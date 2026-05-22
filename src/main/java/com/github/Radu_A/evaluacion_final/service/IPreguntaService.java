package com.github.Radu_A.evaluacion_final.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.Radu_A.evaluacion_final.dto.ResultadoEvaluacion;
import com.github.Radu_A.evaluacion_final.entity.Pregunta;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMúltiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.entity.Tematica;

public interface IPreguntaService {

    Page<Pregunta> filtrarPreguntas(Long tematicaId, String tipoPregunta, Pageable pageable);

    List<Tematica> obtenerTematicas();

    List<PreguntaVerdaderoFalso> obtenerPreguntasVF();

    List<PreguntaSeleccionUnica> obtenerPreguntasSU();

    List<PreguntaSeleccionMúltiple> obtenerPreguntasSM();

    ResultadoEvaluacion<Boolean> evaluarVF(Map<String, String> parametros);

    ResultadoEvaluacion<String> evaluarSU(Map<String, String> parametros);

    ResultadoEvaluacion<List<String>> evaluarSM(Map<String, String[]> parametros);

    Pregunta guardar(Pregunta pregunta);
}