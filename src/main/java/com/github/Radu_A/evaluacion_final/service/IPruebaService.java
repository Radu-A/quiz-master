package com.github.Radu_A.evaluacion_final.service;

import java.util.List;

import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMúltiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;

public interface IPruebaService {

    List<PreguntaVerdaderoFalso> obtenerPreguntasVF();

    List<PreguntaSeleccionUnica> obtenerPreguntasSU();

    List<PreguntaSeleccionMúltiple> obtenerPreguntasSM();

    boolean verificarVF(Long preguntaId, boolean respuesta);

    boolean verificarSU(Long preguntaId, String respuesta);

    boolean verificarSM(Long preguntaId, List<String> respuestasSeleccionadas);
}
