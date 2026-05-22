package com.github.Radu_A.evaluacion_final.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.Radu_A.evaluacion_final.dto.ResultadoEvaluacion;
import com.github.Radu_A.evaluacion_final.entity.Pregunta;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMúltiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.service.IPreguntaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pregunta")
public class PreguntaController {

    @Autowired
    private IPreguntaService preguntaService;

    @GetMapping("/verdadero-falso")
    public String verdaderoFalso(Model model) {
        var preguntas = preguntaService.obtenerPreguntasVF();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        return "pregunta/verdadero-falso";
    }

    @PostMapping("/verdadero-falso")
    public String evaluarVF(@RequestParam Map<String, String> parametros, Model model) {
        ResultadoEvaluacion<Boolean> resultado = preguntaService.evaluarVF(parametros);
        model.addAttribute("preguntas", resultado.preguntas());
        model.addAttribute("resultados", resultado.resultados());
        model.addAttribute("respuestasUsuario", resultado.respuestasUsuario());
        model.addAttribute("puntuacion", resultado.puntuacion());
        model.addAttribute("total", resultado.total());
        model.addAttribute("enviado", true);
        return "pregunta/verdadero-falso";
    }

    @GetMapping("/seleccion-unica")
    public String seleccionUnica(Model model) {
        var preguntas = preguntaService.obtenerPreguntasSU();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        return "pregunta/seleccion-unica";
    }

    @PostMapping("/seleccion-unica")
    public String evaluarSU(@RequestParam Map<String, String> parametros, Model model) {
        ResultadoEvaluacion<String> resultado = preguntaService.evaluarSU(parametros);
        model.addAttribute("preguntas", resultado.preguntas());
        model.addAttribute("resultados", resultado.resultados());
        model.addAttribute("respuestasUsuario", resultado.respuestasUsuario());
        model.addAttribute("puntuacion", resultado.puntuacion());
        model.addAttribute("total", resultado.total());
        model.addAttribute("enviado", true);
        return "pregunta/seleccion-unica";
    }

    @GetMapping("/seleccion-multiple")
    public String seleccionMultiple(Model model) {
        var preguntas = preguntaService.obtenerPreguntasSM();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        return "pregunta/seleccion-multiple";
    }

    @PostMapping("/seleccion-multiple")
    public String evaluarSM(HttpServletRequest request, Model model) {
        ResultadoEvaluacion<List<String>> resultado = preguntaService.evaluarSM(request.getParameterMap());
        model.addAttribute("preguntas", resultado.preguntas());
        model.addAttribute("resultados", resultado.resultados());
        model.addAttribute("respuestasUsuario", resultado.respuestasUsuario());
        model.addAttribute("puntuacion", resultado.puntuacion());
        model.addAttribute("total", resultado.total());
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

        Page<Pregunta> preguntas = preguntaService.filtrarPreguntas(tematicaId, tipoPregunta, pageable);

        model.addAttribute("preguntas", preguntas);
        model.addAttribute("tematicas", preguntaService.obtenerTematicas());
        model.addAttribute("filtroTematicaId", tematicaId);
        model.addAttribute("filtroTipoPregunta", tipoPregunta);

        return "pregunta/menu";
    }
}