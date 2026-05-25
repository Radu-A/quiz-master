package com.github.Radu_A.evaluacion_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.Radu_A.evaluacion_final.dto.PreguntaDto;
import com.github.Radu_A.evaluacion_final.dto.PreguntaSeleccionMultipleDto;
import com.github.Radu_A.evaluacion_final.dto.PreguntaSeleccionUnicaDto;
import com.github.Radu_A.evaluacion_final.dto.PreguntaVerdaderoFalsoDto;
import com.github.Radu_A.evaluacion_final.entity.Pregunta;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMultiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.entity.Tematica;
import com.github.Radu_A.evaluacion_final.repository.TematicaRepository;
import com.github.Radu_A.evaluacion_final.service.IPreguntaService;

@RestController
@RequestMapping("/api")
public class PreguntaRestController {

    @Autowired
    private IPreguntaService preguntaService;

    @Autowired
    private TematicaRepository tematicaRepository;

    @GetMapping("/preguntas")
    public ResponseEntity<?> listarPreguntas(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            Page<Pregunta> pagina = preguntaService.filtrarPreguntas(null, null, pageable);
            Page<PreguntaDto> dtoPage = pagina.map(this::toDto);
            return ResponseEntity.ok(dtoPage);
        }

        List<Pregunta> todas = preguntaService.obtenerPreguntasByTematica(null);
        List<PreguntaDto> dtos = todas.stream().map(this::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/preguntas/{id}")
    public ResponseEntity<PreguntaDto> obtenerPregunta(@PathVariable Long id) {
        Pregunta p = preguntaService.obtenerPorId(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(p));
    }

    @PostMapping("/preguntas")
    public ResponseEntity<PreguntaDto> crearPregunta(@RequestBody PreguntaDto dto) {
        Pregunta entity = toEntity(dto, null);
        Pregunta guardada = preguntaService.guardar(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(guardada));
    }

    @PutMapping("/preguntas/{id}")
    public ResponseEntity<PreguntaDto> actualizarPregunta(
            @PathVariable Long id,
            @RequestBody PreguntaDto dto) {
        if (preguntaService.obtenerPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Pregunta entity = toEntity(dto, id);
        Pregunta guardada = preguntaService.guardar(entity);
        return ResponseEntity.ok(toDto(guardada));
    }

    @DeleteMapping("/preguntas/{id}")
    public ResponseEntity<Void> eliminarPregunta(@PathVariable Long id) {
        if (preguntaService.obtenerPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        preguntaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private Pregunta toEntity(PreguntaDto dto, Long id) {
        Tematica tematica = tematicaRepository.findById(dto.tematicaId()).orElseThrow(
                () -> new IllegalArgumentException("Temática no encontrada: " + dto.tematicaId()));

        Pregunta entity;
        if (dto instanceof PreguntaSeleccionUnicaDto su) {
            PreguntaSeleccionUnica p = new PreguntaSeleccionUnica();
            p.setEnunciado(su.enunciado());
            p.setTematica(tematica);
            p.setOpciones(su.opciones());
            p.setOpcionCorrecta(su.opcionCorrecta());
            entity = p;
        } else if (dto instanceof PreguntaSeleccionMultipleDto sm) {
            PreguntaSeleccionMultiple p = new PreguntaSeleccionMultiple();
            p.setEnunciado(sm.enunciado());
            p.setTematica(tematica);
            p.setOpciones(sm.opciones());
            p.setOpcionesCorrectas(sm.opcionesCorrectas());
            entity = p;
        } else if (dto instanceof PreguntaVerdaderoFalsoDto vf) {
            PreguntaVerdaderoFalso p = new PreguntaVerdaderoFalso();
            p.setEnunciado(vf.enunciado());
            p.setTematica(tematica);
            p.setRespuestaCorrecta(vf.respuestaCorrecta());
            entity = p;
        } else {
            throw new IllegalArgumentException("Tipo de DTO desconocido: " + dto.getClass());
        }

        if (id != null) {
            entity.setId(id);
        }
        return entity;
    }

    private PreguntaDto toDto(Pregunta p) {
        if (p instanceof PreguntaVerdaderoFalso vf) {
            return new PreguntaVerdaderoFalsoDto(
                    vf.getId(), vf.getEnunciado(), vf.getTematica().getId(),
                    vf.isRespuestaCorrecta());
        }
        if (p instanceof PreguntaSeleccionUnica su) {
            return new PreguntaSeleccionUnicaDto(
                    su.getId(), su.getEnunciado(), su.getTematica().getId(),
                    su.getOpciones(), su.getOpcionCorrecta());
        }
        if (p instanceof PreguntaSeleccionMultiple sm) {
            return new PreguntaSeleccionMultipleDto(
                    sm.getId(), sm.getEnunciado(), sm.getTematica().getId(),
                    sm.getOpciones(), sm.getOpcionesCorrectas());
        }
        throw new IllegalArgumentException("Tipo de pregunta desconocido: " + p.getClass());
    }
}
