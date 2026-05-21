package com.github.Radu_A.evaluacion_final.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "preguntas")
// Usamos SINGLE_TABLE: todas las preguntas compartirán la misma tabla, 
// y una columna llamada "tipo_pregunta" diferenciará cuál es cuál.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pregunta", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Pregunta { // Se recomienda "abstract" para no instanciar una pregunta genérica

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String enunciado;

	// Bloque 5: Relación ManyToOne. Muchas preguntas pertenecen a una Temática.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tematica_id", nullable = false)
	private Tematica tematica;
}