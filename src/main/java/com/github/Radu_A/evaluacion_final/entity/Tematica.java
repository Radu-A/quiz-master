package com.github.Radu_A.evaluacion_final.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "tematicas")
@Getter @Setter
public class Tematica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String icono;

    // Relación bidireccional (opcional, pero útil para listar preguntas desde una temática)
    @OneToMany(mappedBy = "tematica", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas;
}
