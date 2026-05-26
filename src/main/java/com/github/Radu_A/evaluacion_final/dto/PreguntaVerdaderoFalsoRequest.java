package com.github.Radu_A.evaluacion_final.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PreguntaVerdaderoFalsoRequest(
    @NotBlank String enunciado,
    @NotNull Long tematicaId,
    boolean respuestaCorrecta
) {
}
