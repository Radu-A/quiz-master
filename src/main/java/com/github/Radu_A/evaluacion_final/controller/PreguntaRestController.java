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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Preguntas", description = "API REST para la gestión de preguntas")
public class PreguntaRestController {

    @Autowired
    private IPreguntaService preguntaService;

    @Autowired
    private TematicaRepository tematicaRepository;

    @GetMapping("/preguntas")
    @Operation(summary = "Listar preguntas", description = "Obtiene todas las preguntas. Si se proporcionan page y size, devuelve una lista paginada; de lo contrario, devuelve todas las preguntas.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de preguntas obtenida correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(oneOf = { PreguntaSeleccionUnicaDto.class, PreguntaSeleccionMultipleDto.class, PreguntaVerdaderoFalsoDto.class })))
    })
    public ResponseEntity<?> listarPreguntas(
            @Parameter(description = "Número de página (empezando en 0)")
            @RequestParam(required = false) Integer page,
            @Parameter(description = "Tamaño de página")
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
    @Operation(summary = "Obtener pregunta por ID", description = "Devuelve una pregunta según su identificador único.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pregunta encontrada",
            content = @Content(mediaType = "application/json",
                schema = @Schema(oneOf = { PreguntaSeleccionUnicaDto.class, PreguntaSeleccionMultipleDto.class, PreguntaVerdaderoFalsoDto.class }))),
        @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    })
    public ResponseEntity<PreguntaDto> obtenerPregunta(
            @Parameter(description = "ID de la pregunta") @PathVariable Long id) {
        Pregunta p = preguntaService.obtenerPorId(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(p));
    }

    @PostMapping("/preguntas")
    @Operation(summary = "Crear pregunta", description = "Crea una nueva pregunta. El tipo se determina mediante la propiedad 'tipoPregunta' en el JSON.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pregunta creada correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(oneOf = { PreguntaSeleccionUnicaDto.class, PreguntaSeleccionMultipleDto.class, PreguntaVerdaderoFalsoDto.class }))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<PreguntaDto> crearPregunta(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos de la pregunta a crear",
                required = true,
                content = @Content(schema = @Schema(oneOf = { PreguntaSeleccionUnicaDto.class, PreguntaSeleccionMultipleDto.class, PreguntaVerdaderoFalsoDto.class })))
            @RequestBody PreguntaDto dto) {
        Pregunta entity = toEntity(dto, null);
        Pregunta guardada = preguntaService.guardar(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(guardada));
    }

    @PutMapping("/preguntas/{id}")
    @Operation(summary = "Actualizar pregunta", description = "Actualiza una pregunta existente por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pregunta actualizada correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(oneOf = { PreguntaSeleccionUnicaDto.class, PreguntaSeleccionMultipleDto.class, PreguntaVerdaderoFalsoDto.class }))),
        @ApiResponse(responseCode = "404", description = "Pregunta no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<PreguntaDto> actualizarPregunta(
            @Parameter(description = "ID de la pregunta a actualizar") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos actualizados de la pregunta",
                required = true,
                content = @Content(schema = @Schema(oneOf = { PreguntaSeleccionUnicaDto.class, PreguntaSeleccionMultipleDto.class, PreguntaVerdaderoFalsoDto.class })))
            @RequestBody PreguntaDto dto) {
        if (preguntaService.obtenerPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Pregunta entity = toEntity(dto, id);
        Pregunta guardada = preguntaService.guardar(entity);
        return ResponseEntity.ok(toDto(guardada));
    }

    @DeleteMapping("/preguntas/{id}")
    @Operation(summary = "Eliminar pregunta", description = "Elimina una pregunta por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pregunta eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Pregunta no encontrada")
    })
    public ResponseEntity<Void> eliminarPregunta(
            @Parameter(description = "ID de la pregunta a eliminar") @PathVariable Long id) {
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
