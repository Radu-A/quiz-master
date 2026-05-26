package com.github.Radu_A.evaluacion_final.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

// Entidad Rol. mappedBy indica que la FK la gestiona Usuario (lado propietario).
@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Valores esperados: "ROLE_ADMIN", "ROLE_USER"
    private String nombre;

    @ManyToMany(mappedBy = "roles")
    @Builder.Default
    private List<Usuario> usuarios = new ArrayList<>();
}