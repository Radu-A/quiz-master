package com.github.Radu_A.evaluacion_final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMúltiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.repository.PreguntaSeleccionMultipleRepository;
import com.github.Radu_A.evaluacion_final.repository.PreguntaSeleccionUnicaRepository;
import com.github.Radu_A.evaluacion_final.repository.PreguntaVerdaderoFalsoRepository;

@Service
public class PruebaServiceImpl implements IPruebaService {

    @Autowired
    private PreguntaVerdaderoFalsoRepository vfRepo;

    @Autowired
    private PreguntaSeleccionUnicaRepository suRepo;

    @Autowired
    private PreguntaSeleccionMultipleRepository smRepo;

    @Override
    public List<PreguntaVerdaderoFalso> obtenerPreguntasVF() {
        return vfRepo.findAll();
    }

    @Override
    public List<PreguntaSeleccionUnica> obtenerPreguntasSU() {
        return suRepo.findAll();
    }

    @Override
    public List<PreguntaSeleccionMúltiple> obtenerPreguntasSM() {
        return smRepo.findAll();
    }

    @Override
    public boolean verificarVF(Long preguntaId, boolean respuesta) {
        return vfRepo.findById(preguntaId)
                .map(p -> p.isRespuestaCorrecta() == respuesta)
                .orElse(false);
    }

    @Override
    public boolean verificarSU(Long preguntaId, String respuesta) {
        return suRepo.findById(preguntaId)
                .map(p -> p.getOpcionCorrecta().equals(respuesta))
                .orElse(false);
    }

    @Override
    public boolean verificarSM(Long preguntaId, List<String> respuestasSeleccionadas) {
        return smRepo.findById(preguntaId).map(p -> {
            List<String> correctas = p.getOpcionesCorrectas();
            if (respuestasSeleccionadas == null || respuestasSeleccionadas.isEmpty()) {
                return correctas == null || correctas.isEmpty();
            }
            return correctas.containsAll(respuestasSeleccionadas)
                    && respuestasSeleccionadas.containsAll(correctas);
        }).orElse(false);
    }
}
