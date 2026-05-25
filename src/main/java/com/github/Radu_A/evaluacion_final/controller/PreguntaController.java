package com.github.Radu_A.evaluacion_final.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.Radu_A.evaluacion_final.dto.PreguntaForm;
import com.github.Radu_A.evaluacion_final.dto.ResultadoEvaluacion;
import com.github.Radu_A.evaluacion_final.dto.ResultadoQuiz;
import com.github.Radu_A.evaluacion_final.exception.PreguntaNoEncontradaException;

import jakarta.validation.Valid;
import com.github.Radu_A.evaluacion_final.entity.Pregunta;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMultiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.entity.Tematica;
import com.github.Radu_A.evaluacion_final.repository.TematicaRepository;
import com.github.Radu_A.evaluacion_final.service.IPreguntaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pregunta")
public class PreguntaController {

    @Autowired
    private IPreguntaService preguntaService;

    @Autowired
    private TematicaRepository tematicaRepository;

    @GetMapping("/verdadero-falso")
    public String verdaderoFalso(@RequestParam(required = false) Long tematicaId, Model model) {
        var preguntas = tematicaId != null
                ? preguntaService.obtenerPreguntasVFByTematica(tematicaId)
                : preguntaService.obtenerPreguntasVF();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        model.addAttribute("tematicaId", tematicaId);
        return "pregunta/verdadero-falso";
    }

    @PostMapping("/verdadero-falso")
    public String evaluarVF(@RequestParam Map<String, String> parametros,
                            @RequestParam(required = false) Long tematicaId,
                            Model model) {
        ResultadoEvaluacion<Boolean> resultado = preguntaService.evaluarVF(parametros);
        model.addAttribute("preguntas", resultado.preguntas());
        model.addAttribute("resultados", resultado.resultados());
        model.addAttribute("respuestasUsuario", resultado.respuestasUsuario());
        model.addAttribute("puntuacion", resultado.puntuacion());
        model.addAttribute("total", resultado.total());
        model.addAttribute("enviado", true);
        model.addAttribute("tematicaId", tematicaId);
        return "pregunta/verdadero-falso";
    }

    @GetMapping("/seleccion-unica")
    public String seleccionUnica(@RequestParam(required = false) Long tematicaId, Model model) {
        var preguntas = tematicaId != null
                ? preguntaService.obtenerPreguntasSUByTematica(tematicaId)
                : preguntaService.obtenerPreguntasSU();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        model.addAttribute("tematicaId", tematicaId);
        return "pregunta/seleccion-unica";
    }

    @PostMapping("/seleccion-unica")
    public String evaluarSU(@RequestParam Map<String, String> parametros,
                            @RequestParam(required = false) Long tematicaId,
                            Model model) {
        ResultadoEvaluacion<String> resultado = preguntaService.evaluarSU(parametros);
        model.addAttribute("preguntas", resultado.preguntas());
        model.addAttribute("resultados", resultado.resultados());
        model.addAttribute("respuestasUsuario", resultado.respuestasUsuario());
        model.addAttribute("puntuacion", resultado.puntuacion());
        model.addAttribute("total", resultado.total());
        model.addAttribute("enviado", true);
        model.addAttribute("tematicaId", tematicaId);
        return "pregunta/seleccion-unica";
    }

    @GetMapping("/seleccion-multiple")
    public String seleccionMultiple(@RequestParam(required = false) Long tematicaId, Model model) {
        var preguntas = tematicaId != null
                ? preguntaService.obtenerPreguntasSMByTematica(tematicaId)
                : preguntaService.obtenerPreguntasSM();
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("enviado", false);
        model.addAttribute("total", preguntas.size());
        model.addAttribute("tematicaId", tematicaId);
        return "pregunta/seleccion-multiple";
    }

    @PostMapping("/seleccion-multiple")
    public String evaluarSM(HttpServletRequest request,
                            @RequestParam(required = false) Long tematicaId,
                            Model model) {
        ResultadoEvaluacion<List<String>> resultado = preguntaService.evaluarSM(request.getParameterMap());
        model.addAttribute("preguntas", resultado.preguntas());
        model.addAttribute("resultados", resultado.resultados());
        model.addAttribute("respuestasUsuario", resultado.respuestasUsuario());
        model.addAttribute("puntuacion", resultado.puntuacion());
        model.addAttribute("total", resultado.total());
        model.addAttribute("enviado", true);
        model.addAttribute("tematicaId", tematicaId);
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

    @GetMapping("/quiz")
    public String quiz(@RequestParam(required = false) String tematicaId, Model model) {
        Long tematicaIdLong = null;
        Tematica tematica = null;
        if (tematicaId != null && !tematicaId.isBlank()) {
            tematica = tematicaRepository.findBySlug(tematicaId).orElse(null);
            if (tematica != null) {
                tematicaIdLong = tematica.getId();
            }
        }
        var preguntas = preguntaService.obtenerPreguntasQuiz(tematicaIdLong);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("total", preguntas.size());
        model.addAttribute("tematicaId", tematicaId);
        model.addAttribute("tematica", tematica);
        return "pregunta/quiz";
    }

    @PostMapping("/quiz")
    public String evaluarQuiz(@RequestParam(required = false) String tematicaId,
                              HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Long tematicaIdLong = null;
        Tematica tematica = null;
        if (tematicaId != null && !tematicaId.isBlank()) {
            tematica = tematicaRepository.findBySlug(tematicaId).orElse(null);
            if (tematica != null) {
                tematicaIdLong = tematica.getId();
            }
        }
        var preguntas = preguntaService.obtenerPreguntasQuiz(tematicaIdLong);
        ResultadoQuiz resultado = preguntaService.evaluarQuiz(preguntas, request.getParameterMap());
        redirectAttributes.addFlashAttribute("preguntas", resultado.preguntas());
        redirectAttributes.addFlashAttribute("resultados", resultado.resultados());
        redirectAttributes.addFlashAttribute("puntuacion", resultado.puntuacion());
        redirectAttributes.addFlashAttribute("total", resultado.total());
        redirectAttributes.addFlashAttribute("tematicaId", tematicaId);
        if (tematica != null) {
            redirectAttributes.addFlashAttribute("tematicaNombre", tematica.getNombre());
            redirectAttributes.addFlashAttribute("tematicaColor", tematica.getColor());
            redirectAttributes.addFlashAttribute("tematicaDescripcion", tematica.getDescripcion());
        }
        return "redirect:/pregunta/resultado";
    }

    @GetMapping("/resultado")
    public String resultado(Model model) {
        if (!model.containsAttribute("preguntas")) {
            return "redirect:/pregunta/quiz";
        }
        return "pregunta/resultado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        preguntaService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Pregunta eliminada correctamente");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/pregunta/menu";
    }

    @GetMapping("/formulario-pregunta")
    public String crearForm(Model model) {
        model.addAttribute("tematicas", preguntaService.obtenerTematicas());
        model.addAttribute("editMode", false);
        if (!model.containsAttribute("preguntaForm")) {
            model.addAttribute("preguntaForm", new PreguntaForm());
        }
        return "pregunta/formulario-pregunta";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Pregunta pregunta = preguntaService.obtenerPorId(id);
        if (pregunta == null) {
            throw new PreguntaNoEncontradaException(id);
        }

        model.addAttribute("tematicas", preguntaService.obtenerTematicas());
        model.addAttribute("pregunta", pregunta);

        String tipoPregunta = "";
        if (pregunta instanceof PreguntaVerdaderoFalso) {
            tipoPregunta = "VERDADERO_FALSO";
            PreguntaVerdaderoFalso vf = (PreguntaVerdaderoFalso) pregunta;
            model.addAttribute("respuestaCorrecta", Boolean.toString(vf.isRespuestaCorrecta()));
        } else if (pregunta instanceof PreguntaSeleccionUnica) {
            tipoPregunta = "SELECCION_UNICA";
            PreguntaSeleccionUnica su = (PreguntaSeleccionUnica) pregunta;
            model.addAttribute("opciones", su.getOpciones());
            model.addAttribute("opcionCorrecta", su.getOpcionCorrecta());
        } else if (pregunta instanceof PreguntaSeleccionMultiple) {
            tipoPregunta = "SELECCION_MULTIPLE";
            PreguntaSeleccionMultiple sm = (PreguntaSeleccionMultiple) pregunta;
            model.addAttribute("opciones", sm.getOpciones());
            model.addAttribute("opcionesCorrectas", sm.getOpcionesCorrectas());
        }

        if (!model.containsAttribute("preguntaForm")) {
            PreguntaForm form = new PreguntaForm();
            form.setEnunciado(pregunta.getEnunciado());
            form.setTematicaId(String.valueOf(pregunta.getTematica().getId()));
            form.setTipoPregunta(tipoPregunta);
            model.addAttribute("preguntaForm", form);
        }

        model.addAttribute("tipoPregunta", tipoPregunta);
        model.addAttribute("enunciado", pregunta.getEnunciado());
        model.addAttribute("tematicaId", pregunta.getTematica().getId());
        model.addAttribute("editMode", true);
        model.addAttribute("preguntaId", id);

        return "pregunta/formulario-pregunta";
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         @Valid @ModelAttribute("preguntaForm") PreguntaForm form,
                         BindingResult result,
                         HttpServletRequest request,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("tematicas", preguntaService.obtenerTematicas());
            model.addAttribute("editMode", true);
            model.addAttribute("preguntaId", id);
            model.addAttribute("tipoPregunta", form.getTipoPregunta());
            return "pregunta/formulario-pregunta";
        }

        var paramMap = request.getParameterMap();
        Long tematicaId = Long.parseLong(form.getTematicaId());
        Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow();

        Pregunta preguntaExistente = preguntaService.obtenerPorId(id);
        if (preguntaExistente == null) {
            redirectAttributes.addFlashAttribute("mensaje", "Pregunta no encontrada.");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/pregunta/menu";
        }

        switch (form.getTipoPregunta()) {
            case "VERDADERO_FALSO" -> {
                PreguntaVerdaderoFalso p = (PreguntaVerdaderoFalso) preguntaExistente;
                p.setEnunciado(form.getEnunciado());
                p.setTematica(tematica);
                p.setRespuestaCorrecta(Boolean.parseBoolean(getParam(paramMap, "respuestaCorrecta")));
                preguntaService.guardar(p);
            }
            case "SELECCION_UNICA" -> {
                PreguntaSeleccionUnica p = (PreguntaSeleccionUnica) preguntaExistente;
                p.setEnunciado(form.getEnunciado());
                p.setTematica(tematica);
                List<String> opciones = collectIndexedParams(paramMap, "opcion_");
                p.setOpciones(opciones);
                int correctaIdx = Integer.parseInt(getParam(paramMap, "opcionCorrecta"));
                p.setOpcionCorrecta(opciones.get(correctaIdx));
                preguntaService.guardar(p);
            }
            case "SELECCION_MULTIPLE" -> {
                PreguntaSeleccionMultiple p = (PreguntaSeleccionMultiple) preguntaExistente;
                p.setEnunciado(form.getEnunciado());
                p.setTematica(tematica);
                List<String> opciones = collectIndexedParams(paramMap, "opcion_");
                p.setOpciones(opciones);
                List<String> correctas = collectCorrectasFromIndices(paramMap, opciones);
                p.setOpcionesCorrectas(correctas);
                preguntaService.guardar(p);
            }
        }

        redirectAttributes.addFlashAttribute("mensaje", "Pregunta actualizada correctamente");
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        return "redirect:/pregunta/menu";
    }

    @PostMapping("/formulario-pregunta")
    public String crear(@Valid @ModelAttribute("preguntaForm") PreguntaForm form,
                        BindingResult result,
                        HttpServletRequest request,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("tematicas", preguntaService.obtenerTematicas());
            model.addAttribute("editMode", false);
            model.addAttribute("tipoPregunta", form.getTipoPregunta());
            return "pregunta/formulario-pregunta";
        }

        var paramMap = request.getParameterMap();
        Long tematicaId = Long.parseLong(form.getTematicaId());
        Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow();

        switch (form.getTipoPregunta()) {
            case "VERDADERO_FALSO" -> {
                PreguntaVerdaderoFalso p = new PreguntaVerdaderoFalso();
                p.setEnunciado(form.getEnunciado());
                p.setTematica(tematica);
                p.setRespuestaCorrecta(Boolean.parseBoolean(getParam(paramMap, "respuestaCorrecta")));
                preguntaService.guardar(p);
            }
            case "SELECCION_UNICA" -> {
                PreguntaSeleccionUnica p = new PreguntaSeleccionUnica();
                p.setEnunciado(form.getEnunciado());
                p.setTematica(tematica);
                List<String> opciones = collectIndexedParams(paramMap, "opcion_");
                p.setOpciones(opciones);
                int correctaIdx = Integer.parseInt(getParam(paramMap, "opcionCorrecta"));
                p.setOpcionCorrecta(opciones.get(correctaIdx));
                preguntaService.guardar(p);
            }
            case "SELECCION_MULTIPLE" -> {
                PreguntaSeleccionMultiple p = new PreguntaSeleccionMultiple();
                p.setEnunciado(form.getEnunciado());
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

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("preguntaForm") PreguntaForm form,
                          BindingResult result,
                          HttpServletRequest request,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("tematicas", preguntaService.obtenerTematicas());
            model.addAttribute("editMode", false);
            model.addAttribute("tipoPregunta", form.getTipoPregunta());
            return "pregunta/formulario-pregunta";
        }

        var paramMap = request.getParameterMap();
        Long tematicaId = Long.parseLong(form.getTematicaId());
        Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow();

        switch (form.getTipoPregunta()) {
            case "VERDADERO_FALSO" -> {
                PreguntaVerdaderoFalso p = new PreguntaVerdaderoFalso();
                p.setEnunciado(form.getEnunciado());
                p.setTematica(tematica);
                p.setRespuestaCorrecta(Boolean.parseBoolean(getParam(paramMap, "respuestaCorrecta")));
                preguntaService.guardar(p);
            }
            case "SELECCION_UNICA" -> {
                PreguntaSeleccionUnica p = new PreguntaSeleccionUnica();
                p.setEnunciado(form.getEnunciado());
                p.setTematica(tematica);
                List<String> opciones = collectIndexedParams(paramMap, "opcion_");
                p.setOpciones(opciones);
                int correctaIdx = Integer.parseInt(getParam(paramMap, "opcionCorrecta"));
                p.setOpcionCorrecta(opciones.get(correctaIdx));
                preguntaService.guardar(p);
            }
            case "SELECCION_MULTIPLE" -> {
                PreguntaSeleccionMultiple p = new PreguntaSeleccionMultiple();
                p.setEnunciado(form.getEnunciado());
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