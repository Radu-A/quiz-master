package com.github.Radu_A.evaluacion_final.dto;

import java.util.List;
import java.util.Map;

import com.github.Radu_A.evaluacion_final.entity.Pregunta;

public record ResultadoQuiz(
        List<Pregunta> preguntas,
        Map<Long, Boolean> resultados,
        int puntuacion,
        int total) {
}
