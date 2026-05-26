package com.github.Radu_A.evaluacion_final.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.Radu_A.evaluacion_final.entity.Usuario;
import com.github.Radu_A.evaluacion_final.repository.IUsuarioRepository;

// Puente entre nuestra entidad JPA Usuario y el contrato de Spring Security.
// Spring Security llama a loadUserByUsername() durante la autenticación.
@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioDetailsService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                "Usuario no encontrado: " + username));

        // Convertimos nuestra lista de Rol en GrantedAuthority.
        // SimpleGrantedAuthority es la implementación más sencilla: solo guarda el String.
        // El prefijo ROLE_ en el nombre permite usar hasRole("ADMIN") en la configuración.
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
            .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
            .collect(Collectors.toList());

        // Usamos la clase User de Spring Security (no la nuestra) como envoltorio estándar.
        // Parámetros: username · passwordHasheado · enabled
        //             · accountNonExpired · credentialsNonExpired · accountNonLocked
        //             · authorities
        return new org.springframework.security.core.userdetails.User(
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.isEnabled(),
            true, true, true,
            authorities
        );
    }
}
