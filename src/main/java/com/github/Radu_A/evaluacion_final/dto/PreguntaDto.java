package com.github.Radu_A.evaluacion_final.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipoPregunta")
@JsonSubTypes({
    @JsonSubTypes.Type(value = PreguntaSeleccionUnicaDto.class, name = "SELECCION_UNICA"),
    @JsonSubTypes.Type(value = PreguntaSeleccionMultipleDto.class, name = "SELECCION_MULTIPLE"),
    @JsonSubTypes.Type(value = PreguntaVerdaderoFalsoDto.class, name = "VERDADERO_FALSO")
})
public sealed interface PreguntaDto
    permits PreguntaSeleccionUnicaDto, PreguntaSeleccionMultipleDto, PreguntaVerdaderoFalsoDto {

    Long id();

    String enunciado();

    Long tematicaId();
}
