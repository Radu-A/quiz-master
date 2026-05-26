package com.github.Radu_A.evaluacion_final.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PreguntaSeleccionUnicaRequest(
    @NotBlank String enunciado,
    @NotNull Long tematicaId,
    @NotEmpty List<String> opciones,
    @NotEmpty List<String> opcionesCorrectas
) {
}
