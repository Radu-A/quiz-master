package com.github.Radu_A.evaluacion_final.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("VERDADERO_FALSO")
@Getter
@Setter
public class PreguntaVerdaderoFalso extends Pregunta {

	// Campo específico: la respuesta correcta es true o false
	private boolean respuestaCorrecta;
}
