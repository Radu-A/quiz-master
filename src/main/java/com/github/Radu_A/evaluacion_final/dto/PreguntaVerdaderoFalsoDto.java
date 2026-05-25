package com.github.Radu_A.evaluacion_final.dto;

public record PreguntaVerdaderoFalsoDto(
    Long id,
    String enunciado,
    Long tematicaId,
    boolean respuestaCorrecta
) implements PreguntaDto {
}
