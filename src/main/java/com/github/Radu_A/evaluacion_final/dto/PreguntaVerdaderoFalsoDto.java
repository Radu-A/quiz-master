package com.github.Radu_A.evaluacion_final.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PreguntaVerdaderoFalsoDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    String enunciado,
    Long tematicaId,
    boolean respuestaCorrecta
) implements PreguntaDto {
}
