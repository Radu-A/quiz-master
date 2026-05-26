package com.github.Radu_A.evaluacion_final.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record PreguntaSeleccionMultipleDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    String enunciado,
    Long tematicaId,
    List<String> opciones,
    List<String> opcionesCorrectas
) implements PreguntaDto {
}
