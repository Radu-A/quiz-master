package com.github.Radu_A.evaluacion_final.dto;

import java.util.List;

public record PreguntaSeleccionMultipleDto(
    Long id,
    String enunciado,
    Long tematicaId,
    List<String> opciones,
    List<String> opcionesCorrectas
) implements PreguntaDto {
}
