package com.github.Radu_A.evaluacion_final.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMúltiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.entity.Tematica;
import com.github.Radu_A.evaluacion_final.repository.TematicaRepository;
import com.github.Radu_A.evaluacion_final.service.IPreguntaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/preguntas")
public class PreguntasController {

    @Autowired
    private IPreguntaService preguntaService;

    @Autowired
    private TematicaRepository tematicaRepository;

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("tematicas", preguntaService.obtenerTematicas());
        return "preguntas/crear";
    }

    @PostMapping("/crear")
    public String crear(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        var paramMap = request.getParameterMap();
        String tipo = getParam(paramMap, "tipoPregunta");
        String enunciado = getParam(paramMap, "enunciado");
        Long tematicaId = Long.parseLong(getParam(paramMap, "tematicaId"));
        Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow();

        switch (tipo) {
            case "VERDADERO_FALSO" -> {
                PreguntaVerdaderoFalso p = new PreguntaVerdaderoFalso();
                p.setEnunciado(enunciado);
                p.setTematica(tematica);
                p.setRespuestaCorrecta(Boolean.parseBoolean(getParam(paramMap, "respuestaCorrecta")));
                preguntaService.guardar(p);
            }
            case "SELECCION_UNICA" -> {
                PreguntaSeleccionUnica p = new PreguntaSeleccionUnica();
                p.setEnunciado(enunciado);
                p.setTematica(tematica);
                List<String> opciones = collectIndexedParams(paramMap, "opcion_");
                p.setOpciones(opciones);
                int correctaIdx = Integer.parseInt(getParam(paramMap, "opcionCorrecta"));
                p.setOpcionCorrecta(opciones.get(correctaIdx));
                preguntaService.guardar(p);
            }
            case "SELECCION_MULTIPLE" -> {
                PreguntaSeleccionMúltiple p = new PreguntaSeleccionMúltiple();
                p.setEnunciado(enunciado);
                p.setTematica(tematica);
                List<String> opciones = collectIndexedParams(paramMap, "opcion_");
                p.setOpciones(opciones);
                List<String> correctas = collectCorrectasFromIndices(paramMap, opciones);
                p.setOpcionesCorrectas(correctas);
                preguntaService.guardar(p);
            }
        }

        redirectAttributes.addFlashAttribute("mensaje", "Pregunta creada correctamente");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/pregunta/menu";
    }

    private String getParam(java.util.Map<String, String[]> params, String key) {
        String[] values = params.get(key);
        return (values != null && values.length > 0) ? values[0] : null;
    }

    private List<String> collectIndexedParams(java.util.Map<String, String[]> params, String prefix) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (true) {
            String key = prefix + i;
            String[] values = params.get(key);
            if (values == null || values.length == 0 || values[0].isBlank()) {
                break;
            }
            result.add(values[0].trim());
            i++;
        }
        return result;
    }

    private List<String> collectCorrectasFromIndices(java.util.Map<String, String[]> params, List<String> opciones) {
        String[] indices = params.get("opcionesCorrectas");
        if (indices == null) {
            return List.of();
        }
        List<String> correctas = new ArrayList<>();
        for (String idxStr : indices) {
            try {
                int idx = Integer.parseInt(idxStr);
                if (idx >= 0 && idx < opciones.size()) {
                    correctas.add(opciones.get(idx));
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return correctas;
    }
}
