package com.github.Radu_A.evaluacion_final.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.Radu_A.evaluacion_final.entity.Usuario;

// findByUsername es el método que invocará UsuarioDetailsService
// cuando Spring Security necesite cargar un usuario por su nombre de acceso.
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
