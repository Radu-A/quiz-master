package com.github.Radu_A.evaluacion_final.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.Radu_A.evaluacion_final.dto.ResultadoEvaluacion;
import com.github.Radu_A.evaluacion_final.dto.ResultadoQuiz;
import com.github.Radu_A.evaluacion_final.entity.Pregunta;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMultiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.entity.Tematica;

public interface IPreguntaService {

    Page<Pregunta> filtrarPreguntas(Long tematicaId, String tipoPregunta, Pageable pageable);

    List<Tematica> obtenerTematicas();

    List<Pregunta> obtenerPreguntasByTematica(Long tematicaId);

    List<PreguntaVerdaderoFalso> obtenerPreguntasVF();

    List<PreguntaVerdaderoFalso> obtenerPreguntasVFByTematica(Long tematicaId);

    List<PreguntaSeleccionUnica> obtenerPreguntasSU();

    List<PreguntaSeleccionUnica> obtenerPreguntasSUByTematica(Long tematicaId);

    List<PreguntaSeleccionMultiple> obtenerPreguntasSM();

    List<PreguntaSeleccionMultiple> obtenerPreguntasSMByTematica(Long tematicaId);

    ResultadoEvaluacion<Boolean> evaluarVF(Map<String, String> parametros);

    ResultadoEvaluacion<String> evaluarSU(Map<String, String> parametros);

    ResultadoEvaluacion<List<String>> evaluarSM(Map<String, String[]> parametros);

    ResultadoQuiz evaluarQuiz(List<Pregunta> preguntas, Map<String, String[]> allParams);

    List<Pregunta> obtenerPreguntasQuiz(Long tematicaId);

    Pregunta guardar(Pregunta pregunta);

    Pregunta obtenerPorId(Long id);
}