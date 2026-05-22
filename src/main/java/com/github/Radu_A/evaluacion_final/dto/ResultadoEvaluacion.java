package com.github.Radu_A.evaluacion_final.dto;

import java.util.List;
import java.util.Map;

import com.github.Radu_A.evaluacion_final.entity.Pregunta;

public record ResultadoEvaluacion<T>(
        List<? extends Pregunta> preguntas,
        Map<Long, Boolean> resultados,
        Map<Long, T> respuestasUsuario,
        int puntuacion,
        int total) {
}