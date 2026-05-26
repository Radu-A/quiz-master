package com.github.Radu_A.evaluacion_final.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

// Entidad de dominio para autenticación.
// FetchType.EAGER en roles: los necesitamos siempre que cargamos el usuario
// para construir los GrantedAuthority. Con LAZY se dispararía LazyInitializationException
// fuera de una sesión JPA activa.
@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    // NUNCA en texto plano. Se almacena el hash BCrypt.
    @Column(nullable = false)
    private String password;

    private String email;

    @Builder.Default
    private boolean enabled = true;

    // Lado propietario: Usuario gestiona la tabla usuario_roles.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns        = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    @Builder.Default
    private List<Rol> roles = new ArrayList<>();
}