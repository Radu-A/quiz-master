package com.github.Radu_A.evaluacion_final.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.Radu_A.evaluacion_final.entity.Pregunta;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMúltiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.entity.Tematica;
import com.github.Radu_A.evaluacion_final.repository.PreguntaRepository;
import com.github.Radu_A.evaluacion_final.repository.TematicaRepository;
import com.github.Radu_A.evaluacion_final.service.IPruebaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pregunta")
public class PreguntaController {

    @Autowired
    private IPruebaService service;

    @Autowired
    private PreguntaRepository preguntaRepo;

    @Autowired
    private TematicaRepository tematicaRepo;

    @GetMapping("/verdadero-falso")
    public String verdaderoFalso(Model model) {
        List<PreguntaVerdaderoFalso> preguntas = service.obtenerPreguntasVF();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        return "pregunta/verdadero-falso";
    }

    @PostMapping("/verdadero-falso")
    public String evaluarVF(@RequestParam Map<String, String> parametros, Model model) {
        List<PreguntaVerdaderoFalso> preguntas = service.obtenerPreguntasVF();
        int puntuacion = 0;
        Map<Long, Boolean> resultados = new HashMap<>();
        Map<Long, Boolean> respuestasUsuario = new HashMap<>();

        for (PreguntaVerdaderoFalso p : preguntas) {
            String key = "r_" + p.getId();
            if (parametros.containsKey(key)) {
                boolean resp = Boolean.parseBoolean(parametros.get(key));
                boolean correcta = service.verificarVF(p.getId(), resp);
                resultados.put(p.getId(), correcta);
                respuestasUsuario.put(p.getId(), resp);
                if (correcta) puntuacion++;
            } else {
                resultados.put(p.getId(), false);
            }
        }

        model.addAttribute("preguntas", preguntas);
        model.addAttribute("resultados", resultados);
        model.addAttribute("respuestasUsuario", respuestasUsuario);
        model.addAttribute("puntuacion", puntuacion);
        model.addAttribute("total", preguntas.size());
        model.addAttribute("enviado", true);
        return "pregunta/verdadero-falso";
    }

    @GetMapping("/seleccion-unica")
    public String seleccionUnica(Model model) {
        List<PreguntaSeleccionUnica> preguntas = service.obtenerPreguntasSU();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        return "pregunta/seleccion-unica";
    }

    @PostMapping("/seleccion-unica")
    public String evaluarSU(@RequestParam Map<String, String> parametros, Model model) {
        List<PreguntaSeleccionUnica> preguntas = service.obtenerPreguntasSU();
        int puntuacion = 0;
        Map<Long, Boolean> resultados = new HashMap<>();
        Map<Long, String> respuestasUsuario = new HashMap<>();

        for (PreguntaSeleccionUnica p : preguntas) {
            String key = "r_" + p.getId();
            if (parametros.containsKey(key)) {
                String resp = parametros.get(key);
                boolean correcta = service.verificarSU(p.getId(), resp);
                resultados.put(p.getId(), correcta);
                respuestasUsuario.put(p.getId(), resp);
                if (correcta) puntuacion++;
            } else {
                resultados.put(p.getId(), false);
            }
        }

        model.addAttribute("preguntas", preguntas);
        model.addAttribute("resultados", resultados);
        model.addAttribute("respuestasUsuario", respuestasUsuario);
        model.addAttribute("puntuacion", puntuacion);
        model.addAttribute("total", preguntas.size());
        model.addAttribute("enviado", true);
        return "pregunta/seleccion-unica";
    }

    @GetMapping("/seleccion-multiple")
    public String seleccionMultiple(Model model) {
        List<PreguntaSeleccionMúltiple> preguntas = service.obtenerPreguntasSM();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        return "pregunta/seleccion-multiple";
    }

    @PostMapping("/seleccion-multiple")
    public String evaluarSM(HttpServletRequest request, Model model) {
        List<PreguntaSeleccionMúltiple> preguntas = service.obtenerPreguntasSM();
        int puntuacion = 0;
        Map<Long, Boolean> resultados = new HashMap<>();
        Map<Long, List<String>> respuestasUsuario = new HashMap<>();

        for (PreguntaSeleccionMúltiple p : preguntas) {
            String key = "r_" + p.getId();
            String[] valores = request.getParameterValues(key);
            List<String> seleccionadas = (valores != null) ? Arrays.asList(valores) : new ArrayList<>();
            boolean correcta = service.verificarSM(p.getId(), seleccionadas);
            resultados.put(p.getId(), correcta);
            respuestasUsuario.put(p.getId(), seleccionadas);
            if (correcta) puntuacion++;
        }

        model.addAttribute("preguntas", preguntas);
        model.addAttribute("resultados", resultados);
        model.addAttribute("respuestasUsuario", respuestasUsuario);
        model.addAttribute("puntuacion", puntuacion);
        model.addAttribute("total", preguntas.size());
        model.addAttribute("enviado", true);
        return "pregunta/seleccion-multiple";
    }

    @GetMapping("/menu")
    public String menu(@RequestParam(required = false) Long tematicaId,
                       @RequestParam(required = false) String tipoPregunta,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "8") int size,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        // Build specification dynamically
        Specification<Pregunta> spec = Specification.where((root, query, cb) -> cb.conjunction());
        if (tematicaId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tematica").get("id"), tematicaId));
        }
        if (tipoPregunta != null && !tipoPregunta.isBlank()) {
            Class<? extends Pregunta> tipoClass = switch (tipoPregunta) {
                case "VERDADERO_FALSO" -> PreguntaVerdaderoFalso.class;
                case "SELECCION_UNICA" -> PreguntaSeleccionUnica.class;
                case "SELECCION_MULTIPLE" -> PreguntaSeleccionMúltiple.class;
                default -> null;
            };
            if (tipoClass != null) {
                spec = spec.and((root, query, cb) -> cb.equal(root.type(), tipoClass));
            }
        }

        Page<Pregunta> preguntas = preguntaRepo.findAll(spec, pageable);
        List<Tematica> tematicas = tematicaRepo.findAll();

        model.addAttribute("preguntas", preguntas);
        model.addAttribute("tematicas", tematicas);
        model.addAttribute("filtroTematicaId", tematicaId);
        model.addAttribute("filtroTipoPregunta", tipoPregunta);

        return "pregunta/menu";
    }
}
