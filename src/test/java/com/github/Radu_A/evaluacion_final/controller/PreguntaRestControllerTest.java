package com.github.Radu_A.evaluacion_final.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tools.jackson.databind.ObjectMapper;
import com.github.Radu_A.evaluacion_final.dto.PreguntaSeleccionMultipleRequest;
import com.github.Radu_A.evaluacion_final.dto.PreguntaSeleccionUnicaRequest;
import com.github.Radu_A.evaluacion_final.dto.PreguntaVerdaderoFalsoRequest;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMultiple;
import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;
import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;
import com.github.Radu_A.evaluacion_final.entity.Tematica;
import com.github.Radu_A.evaluacion_final.repository.TematicaRepository;
import com.github.Radu_A.evaluacion_final.security.JwtTokenProvider;
import com.github.Radu_A.evaluacion_final.service.IPreguntaService;

@WebMvcTest(PreguntaRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(PreguntaRestControllerTest.TestConfig.class)
class PreguntaRestControllerTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        @Primary
        IPreguntaService preguntaService() {
            return mock(IPreguntaService.class);
        }

        @Bean
        @Primary
        TematicaRepository tematicaRepository() {
            return mock(TematicaRepository.class);
        }

        @Bean
        @Primary
        JwtTokenProvider jwtTokenProvider() {
            return mock(JwtTokenProvider.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPreguntaService preguntaService;

    @Autowired
    private TematicaRepository tematicaRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private Tematica tematica;
    private PreguntaVerdaderoFalso vf;
    private PreguntaSeleccionUnica su;
    private PreguntaSeleccionMultiple sm;

    @BeforeEach
    void setUp() {
        reset(preguntaService, tematicaRepository, jwtTokenProvider);

        tematica = new Tematica();
        tematica.setId(1L);
        tematica.setNombre("Java");
        tematica.setSlug("java");
        tematica.setDescripcion("Fundamentos de Java");
        tematica.setColor("blue");
        tematica.setIcono("java-icon");

        vf = new PreguntaVerdaderoFalso();
        vf.setId(1L);
        vf.setEnunciado("Java es un lenguaje compilado?");
        vf.setTematica(tematica);
        vf.setRespuestaCorrecta(true);

        su = new PreguntaSeleccionUnica();
        su.setId(2L);
        su.setEnunciado("Palabra para definir clase?");
        su.setTematica(tematica);
        su.setOpciones(List.of("class", "struct", "interface", "enum"));
        su.setOpcionesCorrectas(List.of("class"));

        sm = new PreguntaSeleccionMultiple();
        sm.setId(3L);
        sm.setEnunciado("Tipos primitivos?");
        sm.setTematica(tematica);
        sm.setOpciones(List.of("int", "float", "String", "boolean"));
        sm.setOpcionesCorrectas(List.of("int", "float", "boolean"));
    }

    @Nested
    @DisplayName("GET /api/preguntas")
    class ListarPreguntasTests {

        @Test
        @DisplayName("devuelve lista de preguntas como JSON")
        void devuelveListaPreguntas() throws Exception {
            when(preguntaService.obtenerPreguntasByTematica(null)).thenReturn(List.of(vf, su, sm));

            mockMvc.perform(get("/api/preguntas")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.length()").value(3))
                    .andExpect(jsonPath("$[0].enunciado").value("Java es un lenguaje compilado?"))
                    .andExpect(jsonPath("$[0].respuestaCorrecta").value(true))
                    .andExpect(jsonPath("$[1].enunciado").value("Palabra para definir clase?"))
                    .andExpect(jsonPath("$[1].opcionesCorrectas[0]").value("class"))
                    .andExpect(jsonPath("$[2].enunciado").value("Tipos primitivos?"))
                    .andExpect(jsonPath("$[2].opcionesCorrectas.length()").value(3));
        }

        @Test
        @DisplayName("devuelve array vacio si no hay preguntas")
        void devuelveArrayVacio() throws Exception {
            when(preguntaService.obtenerPreguntasByTematica(null)).thenReturn(List.of());

            mockMvc.perform(get("/api/preguntas")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        @DisplayName("soporta paginacion con page y size")
        void soportaPaginacion() throws Exception {
            when(preguntaService.filtrarPreguntas(eq(null), eq(null), any()))
                    .thenReturn(new PageImpl<>(List.of(vf)));

            mockMvc.perform(get("/api/preguntas")
                            .param("page", "0")
                            .param("size", "10")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content.length()").value(1));

            verify(preguntaService).filtrarPreguntas(eq(null), eq(null), any());
        }
    }

    @Nested
    @DisplayName("GET /api/preguntas/{id}")
    class ObtenerPreguntaTests {

        @Test
        @DisplayName("devuelve pregunta V/F por ID")
        void devuelveVF() throws Exception {
            when(preguntaService.obtenerPorId(1L)).thenReturn(vf);

            mockMvc.perform(get("/api/preguntas/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.enunciado").value("Java es un lenguaje compilado?"))
                    .andExpect(jsonPath("$.respuestaCorrecta").value(true));
        }

        @Test
        @DisplayName("devuelve pregunta SU por ID")
        void devuelveSU() throws Exception {
            when(preguntaService.obtenerPorId(2L)).thenReturn(su);

            mockMvc.perform(get("/api/preguntas/2")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(2))
                    .andExpect(jsonPath("$.opciones.length()").value(4))
                    .andExpect(jsonPath("$.opcionesCorrectas[0]").value("class"));
        }

        @Test
        @DisplayName("devuelve pregunta SM por ID")
        void devuelveSM() throws Exception {
            when(preguntaService.obtenerPorId(3L)).thenReturn(sm);

            mockMvc.perform(get("/api/preguntas/3")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(3))
                    .andExpect(jsonPath("$.opcionesCorrectas.length()").value(3));
        }

        @Test
        @DisplayName("devuelve 404 si no existe")
        void devuelve404() throws Exception {
            when(preguntaService.obtenerPorId(99L)).thenReturn(null);

            mockMvc.perform(get("/api/preguntas/99")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /api/preguntas/verdadero-falso")
    class CrearPreguntaVFTests {

        @Test
        @DisplayName("crea pregunta V/F y devuelve 201")
        void creaVF() throws Exception {
            PreguntaVerdaderoFalsoRequest request = new PreguntaVerdaderoFalsoRequest(
                    "Java es interpretado?", 1L, true);

            when(tematicaRepository.findById(1L)).thenReturn(Optional.of(tematica));
            when(preguntaService.guardar(any(PreguntaVerdaderoFalso.class))).thenReturn(vf);

            mockMvc.perform(post("/api/preguntas/verdadero-falso")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.enunciado").value("Java es un lenguaje compilado?"))
                    .andExpect(jsonPath("$.respuestaCorrecta").value(true));

            verify(preguntaService).guardar(any(PreguntaVerdaderoFalso.class));
        }

        @Test
        @DisplayName("devuelve 400 si el body esta vacio")
        void bodyVacio() throws Exception {
            mockMvc.perform(post("/api/preguntas/verdadero-falso")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("POST /api/preguntas/seleccion-unica")
    class CrearPreguntaSUTests {

        @Test
        @DisplayName("crea pregunta SU y devuelve 201")
        void creaSU() throws Exception {
            PreguntaSeleccionUnicaRequest request = new PreguntaSeleccionUnicaRequest(
                    "Palabra para clase?", 1L,
                    List.of("class", "struct", "interface"), List.of("class"));

            when(tematicaRepository.findById(1L)).thenReturn(Optional.of(tematica));
            when(preguntaService.guardar(any(PreguntaSeleccionUnica.class))).thenReturn(su);

            mockMvc.perform(post("/api/preguntas/seleccion-unica")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(2))
                    .andExpect(jsonPath("$.opciones.length()").value(4))
                    .andExpect(jsonPath("$.opcionesCorrectas[0]").value("class"));

            verify(preguntaService).guardar(any(PreguntaSeleccionUnica.class));
        }

        @Test
        @DisplayName("devuelve 201 con los datos correctos en la respuesta")
        void respuestaContieneDatosCorrectos() throws Exception {
            PreguntaSeleccionUnicaRequest request = new PreguntaSeleccionUnicaRequest(
                    "Otra pregunta SU?", 1L,
                    List.of("a", "b", "c"), List.of("b"));

            when(tematicaRepository.findById(1L)).thenReturn(Optional.of(tematica));
            when(preguntaService.guardar(any(PreguntaSeleccionUnica.class))).thenReturn(su);

            mockMvc.perform(post("/api/preguntas/seleccion-unica")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(2))
                    .andExpect(jsonPath("$.opcionesCorrectas[0]").value("class"));
        }
    }

    @Nested
    @DisplayName("POST /api/preguntas/seleccion-multiple")
    class CrearPreguntaSMTests {

        @Test
        @DisplayName("crea pregunta SM y devuelve 201")
        void creaSM() throws Exception {
            PreguntaSeleccionMultipleRequest request = new PreguntaSeleccionMultipleRequest(
                    "Tipos primitivos?", 1L,
                    List.of("int", "float", "String", "boolean"),
                    List.of("int", "float", "boolean"));

            when(tematicaRepository.findById(1L)).thenReturn(Optional.of(tematica));
            when(preguntaService.guardar(any(PreguntaSeleccionMultiple.class))).thenReturn(sm);

            mockMvc.perform(post("/api/preguntas/seleccion-multiple")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(3))
                    .andExpect(jsonPath("$.opcionesCorrectas.length()").value(3));

            verify(preguntaService).guardar(any(PreguntaSeleccionMultiple.class));
        }
    }

    @Nested
    @DisplayName("PUT /api/preguntas/{id}/verdadero-falso")
    class ActualizarPreguntaVFTests {

        @Test
        @DisplayName("actualiza pregunta V/F existente")
        void actualizaVF() throws Exception {
            PreguntaVerdaderoFalsoRequest request = new PreguntaVerdaderoFalsoRequest(
                    "Java actualizado?", 1L, false);

            when(preguntaService.obtenerPorId(1L)).thenReturn(vf);
            when(tematicaRepository.findById(1L)).thenReturn(Optional.of(tematica));

            PreguntaVerdaderoFalso actualizada = new PreguntaVerdaderoFalso();
            actualizada.setId(1L);
            actualizada.setEnunciado("Java actualizado?");
            actualizada.setTematica(tematica);
            actualizada.setRespuestaCorrecta(false);
            when(preguntaService.guardar(any(PreguntaVerdaderoFalso.class))).thenReturn(actualizada);

            mockMvc.perform(put("/api/preguntas/1/verdadero-falso")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.enunciado").value("Java actualizado?"))
                    .andExpect(jsonPath("$.respuestaCorrecta").value(false));
        }

        @Test
        @DisplayName("devuelve 404 si la pregunta no existe")
        void devuelve404() throws Exception {
            PreguntaVerdaderoFalsoRequest request = new PreguntaVerdaderoFalsoRequest(
                    "Test", 1L, true);

            when(preguntaService.obtenerPorId(99L)).thenReturn(null);

            mockMvc.perform(put("/api/preguntas/99/verdadero-falso")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isNotFound());

            verify(preguntaService, never()).guardar(any());
        }
    }

    @Nested
    @DisplayName("PUT /api/preguntas/{id}/seleccion-unica")
    class ActualizarPreguntaSUTests {

        @Test
        @DisplayName("actualiza pregunta SU existente")
        void actualizaSU() throws Exception {
            PreguntaSeleccionUnicaRequest request = new PreguntaSeleccionUnicaRequest(
                    "Palabra actualizada?", 1L,
                    List.of("a", "b", "c"), List.of("a"));

            when(preguntaService.obtenerPorId(2L)).thenReturn(su);
            when(tematicaRepository.findById(1L)).thenReturn(Optional.of(tematica));

            PreguntaSeleccionUnica actualizada = new PreguntaSeleccionUnica();
            actualizada.setId(2L);
            actualizada.setEnunciado("Palabra actualizada?");
            actualizada.setTematica(tematica);
            actualizada.setOpciones(List.of("a", "b", "c"));
            actualizada.setOpcionesCorrectas(List.of("a"));
            when(preguntaService.guardar(any(PreguntaSeleccionUnica.class))).thenReturn(actualizada);

            mockMvc.perform(put("/api/preguntas/2/seleccion-unica")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.enunciado").value("Palabra actualizada?"))
                    .andExpect(jsonPath("$.opcionesCorrectas[0]").value("a"));
        }
    }

    @Nested
    @DisplayName("PUT /api/preguntas/{id}/seleccion-multiple")
    class ActualizarPreguntaSMTests {

        @Test
        @DisplayName("actualiza pregunta SM existente")
        void actualizaSM() throws Exception {
            PreguntaSeleccionMultipleRequest request = new PreguntaSeleccionMultipleRequest(
                    "SM actualizada?", 1L,
                    List.of("x", "y", "z"), List.of("x", "z"));

            when(preguntaService.obtenerPorId(3L)).thenReturn(sm);
            when(tematicaRepository.findById(1L)).thenReturn(Optional.of(tematica));

            PreguntaSeleccionMultiple actualizada = new PreguntaSeleccionMultiple();
            actualizada.setId(3L);
            actualizada.setEnunciado("SM actualizada?");
            actualizada.setTematica(tematica);
            actualizada.setOpciones(List.of("x", "y", "z"));
            actualizada.setOpcionesCorrectas(List.of("x", "z"));
            when(preguntaService.guardar(any(PreguntaSeleccionMultiple.class))).thenReturn(actualizada);

            mockMvc.perform(put("/api/preguntas/3/seleccion-multiple")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.enunciado").value("SM actualizada?"))
                    .andExpect(jsonPath("$.opcionesCorrectas.length()").value(2));
        }
    }

    @Nested
    @DisplayName("DELETE /api/preguntas/{id}")
    class EliminarPreguntaTests {

        @Test
        @DisplayName("elimina pregunta existente y devuelve 204")
        void eliminaExistente() throws Exception {
            when(preguntaService.obtenerPorId(1L)).thenReturn(vf);
            doNothing().when(preguntaService).eliminar(1L);

            mockMvc.perform(delete("/api/preguntas/1"))
                    .andExpect(status().isNoContent());

            verify(preguntaService).eliminar(1L);
        }

        @Test
        @DisplayName("devuelve 404 si la pregunta no existe")
        void devuelve404() throws Exception {
            when(preguntaService.obtenerPorId(99L)).thenReturn(null);

            mockMvc.perform(delete("/api/preguntas/99"))
                    .andExpect(status().isNotFound());

            verify(preguntaService, never()).eliminar(any());
        }
    }
}
