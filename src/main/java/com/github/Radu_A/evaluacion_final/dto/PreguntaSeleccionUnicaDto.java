package com.github.Radu_A.evaluacion_final.dto;

import java.util.List;

public record PreguntaSeleccionUnicaDto(
    Long id,
    String enunciado,
    Long tematicaId,
    List<String> opciones,
    String opcionCorrecta
) implements PreguntaDto {
}
