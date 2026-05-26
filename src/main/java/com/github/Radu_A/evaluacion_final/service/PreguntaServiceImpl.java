package com.github.Radu_A.evaluacion_final.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.Radu_A.evaluacion_final.dto.ResultadoEvaluacion;
import com.github.Radu_A.evaluacion_final.dto.ResultadoQuiz;
import com.github.Radu_A.evaluacion_final.entity.Pregunta;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMultiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.entity.Tematica;
import com.github.Radu_A.evaluacion_final.repository.PreguntaRepository;
import com.github.Radu_A.evaluacion_final.repository.PreguntaSeleccionMultipleRepository;
import com.github.Radu_A.evaluacion_final.repository.PreguntaSeleccionUnicaRepository;
import com.github.Radu_A.evaluacion_final.repository.PreguntaVerdaderoFalsoRepository;
import com.github.Radu_A.evaluacion_final.repository.TematicaRepository;

@Service
public class PreguntaServiceImpl implements IPreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepo;

    @Autowired
    private TematicaRepository tematicaRepo;

    @Autowired
    private PreguntaVerdaderoFalsoRepository vfRepo;

    @Autowired
    private PreguntaSeleccionUnicaRepository suRepo;

    @Autowired
    private PreguntaSeleccionMultipleRepository smRepo;

    @Override
    public Page<Pregunta> filtrarPreguntas(Long tematicaId, String tipoPregunta, Pageable pageable) {
        Specification<Pregunta> spec = Specification.where((root, query, cb) -> cb.conjunction());

        if (tematicaId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tematica").get("id"), tematicaId));
        }

        if (tipoPregunta != null && !tipoPregunta.isBlank()) {
            Class<? extends Pregunta> tipoClass = switch (tipoPregunta) {
                case "VERDADERO_FALSO" -> PreguntaVerdaderoFalso.class;
                case "SELECCION_UNICA" -> PreguntaSeleccionUnica.class;
                case "SELECCION_MULTIPLE" -> PreguntaSeleccionMultiple.class;
                default -> null;
            };
            if (tipoClass != null) {
                spec = spec.and((root, query, cb) -> cb.equal(root.type(), tipoClass));
            }
        }

        return preguntaRepo.findAll(spec, pageable);
    }

    @Override
    public List<Tematica> obtenerTematicas() {
        return tematicaRepo.findAll();
    }

    @Override
    public List<Pregunta> obtenerPreguntasByTematica(Long tematicaId) {
        if (tematicaId != null) {
            return preguntaRepo.findByTematicaId(tematicaId);
        }
        return preguntaRepo.findAll();
    }

    @Override
    public List<PreguntaVerdaderoFalso> obtenerPreguntasVF() {
        return vfRepo.findAll();
    }

    @Override
    public List<PreguntaVerdaderoFalso> obtenerPreguntasVFByTematica(Long tematicaId) {
        return vfRepo.findByTematicaId(tematicaId);
    }

    @Override
    public List<PreguntaSeleccionUnica> obtenerPreguntasSU() {
        return suRepo.findAll();
    }

    @Override
    public List<PreguntaSeleccionUnica> obtenerPreguntasSUByTematica(Long tematicaId) {
        return suRepo.findByTematicaId(tematicaId);
    }

    @Override
    public List<PreguntaSeleccionMultiple> obtenerPreguntasSM() {
        return smRepo.findAll();
    }

    @Override
    public List<PreguntaSeleccionMultiple> obtenerPreguntasSMByTematica(Long tematicaId) {
        return smRepo.findByTematicaId(tematicaId);
    }

    @Override
    public ResultadoEvaluacion<Boolean> evaluarVF(Map<String, String> parametros) {
        List<PreguntaVerdaderoFalso> preguntas = vfRepo.findAll();
        int puntuacion = 0;
        Map<Long, Boolean> resultados = new HashMap<>();
        Map<Long, Boolean> respuestasUsuario = new HashMap<>();

        for (PreguntaVerdaderoFalso p : preguntas) {
            String key = "r_" + p.getId();
            if (parametros.containsKey(key)) {
                boolean resp = Boolean.parseBoolean(parametros.get(key));
                boolean correcta = p.isRespuestaCorrecta() == resp;
                resultados.put(p.getId(), correcta);
                respuestasUsuario.put(p.getId(), resp);
                if (correcta) puntuacion++;
            } else {
                resultados.put(p.getId(), false);
            }
        }

        return new ResultadoEvaluacion<>(preguntas, resultados, respuestasUsuario, puntuacion, preguntas.size());
    }

    @Override
    public ResultadoEvaluacion<String> evaluarSU(Map<String, String> parametros) {
        List<PreguntaSeleccionUnica> preguntas = suRepo.findAll();
        int puntuacion = 0;
        Map<Long, Boolean> resultados = new HashMap<>();
        Map<Long, String> respuestasUsuario = new HashMap<>();

        for (PreguntaSeleccionUnica p : preguntas) {
            String key = "r_" + p.getId();
            if (parametros.containsKey(key)) {
                String resp = parametros.get(key);
                boolean correcta = p.getOpcionesCorrectas().get(0).equals(resp);
                resultados.put(p.getId(), correcta);
                respuestasUsuario.put(p.getId(), resp);
                if (correcta) puntuacion++;
            } else {
                resultados.put(p.getId(), false);
            }
        }

        return new ResultadoEvaluacion<>(preguntas, resultados, respuestasUsuario, puntuacion, preguntas.size());
    }

    @Override
    public ResultadoEvaluacion<List<String>> evaluarSM(Map<String, String[]> parametros) {
        List<PreguntaSeleccionMultiple> preguntas = smRepo.findAll();
        int puntuacion = 0;
        Map<Long, Boolean> resultados = new HashMap<>();
        Map<Long, List<String>> respuestasUsuario = new HashMap<>();

        for (PreguntaSeleccionMultiple p : preguntas) {
            String key = "r_" + p.getId();
            String[] valores = parametros.get(key);
            List<String> seleccionadas = (valores != null) ? Arrays.asList(valores) : new ArrayList<>();
            List<String> correctas = p.getOpcionesCorrectas();
            boolean correcta;
            if (seleccionadas.isEmpty()) {
                correcta = correctas == null || correctas.isEmpty();
            } else {
                correcta = correctas.containsAll(seleccionadas) && seleccionadas.containsAll(correctas);
            }
            resultados.put(p.getId(), correcta);
            respuestasUsuario.put(p.getId(), seleccionadas);
            if (correcta) puntuacion++;
        }

        return new ResultadoEvaluacion<>(preguntas, resultados, respuestasUsuario, puntuacion, preguntas.size());
    }

    @Override
    public Pregunta guardar(Pregunta pregunta) {
        return preguntaRepo.save(pregunta);
    }

    @Override
    public Pregunta obtenerPorId(Long id) {
        return preguntaRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        preguntaRepo.deleteById(id);
    }

    @Override
    public ResultadoQuiz evaluarQuiz(List<Pregunta> preguntas, Map<String, String[]> allParams) {
        int puntuacion = 0;
        Map<Long, Boolean> resultados = new HashMap<>();

        for (Pregunta p : preguntas) {
            String key = "r_" + p.getId();
            boolean correcta = false;

            if (p instanceof PreguntaVerdaderoFalso vf) {
                if (allParams.containsKey(key)) {
                    boolean resp = Boolean.parseBoolean(allParams.get(key)[0]);
                    correcta = vf.isRespuestaCorrecta() == resp;
                }
            } else if (p instanceof PreguntaSeleccionUnica su) {
                if (allParams.containsKey(key)) {
                    String resp = allParams.get(key)[0];
                    correcta = su.getOpcionesCorrectas().get(0).equals(resp);
                }
            } else if (p instanceof PreguntaSeleccionMultiple sm) {
                String[] valores = allParams.get(key);
                List<String> seleccionadas = (valores != null) ? Arrays.asList(valores) : new ArrayList<>();
                List<String> correctas = sm.getOpcionesCorrectas();
                if (seleccionadas.isEmpty()) {
                    correcta = correctas == null || correctas.isEmpty();
                } else {
                    correcta = correctas.containsAll(seleccionadas) && seleccionadas.containsAll(correctas);
                }
            }

            resultados.put(p.getId(), correcta);
            if (correcta) puntuacion++;
        }

        return new ResultadoQuiz(preguntas, resultados, puntuacion, preguntas.size());
    }

    @Override
    public List<Pregunta> obtenerPreguntasQuiz(Long tematicaId) {
        List<Pregunta> preguntas = new ArrayList<>();
        List<PreguntaSeleccionUnica> suList = tematicaId != null
                ? suRepo.findByTematicaId(tematicaId)
                : suRepo.findAll();
        List<PreguntaVerdaderoFalso> vfList = tematicaId != null
                ? vfRepo.findByTematicaId(tematicaId)
                : vfRepo.findAll();
        List<PreguntaSeleccionMultiple> smList = tematicaId != null
                ? smRepo.findByTematicaId(tematicaId)
                : smRepo.findAll();
        preguntas.addAll(suList.stream().limit(3).toList());
        preguntas.addAll(vfList.stream().limit(2).toList());
        preguntas.addAll(smList.stream().limit(1).toList());
        return preguntas;
    }
}