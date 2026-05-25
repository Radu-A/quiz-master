package com.github.Radu_A.evaluacion_final.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PreguntaForm {

    private Long id;

    @NotBlank(message = "{pregunta.tipo.obligatorio}")
    private String tipoPregunta;

    @NotBlank(message = "{pregunta.tematica.obligatorio}")
    private String tematicaId;

    @NotBlank(message = "{pregunta.enunciado.obligatorio}")
    @Size(max = 500, message = "{pregunta.enunciado.longitud}")
    private String enunciado;

    private String respuestaCorrecta;
    private String opcionCorrecta;
}
