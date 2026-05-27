package com.github.Radu_A.evaluacion_final.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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

@ExtendWith(MockitoExtension.class)
class PreguntaServiceImplTest {

    @Mock
    private PreguntaRepository preguntaRepo;

    @Mock
    private TematicaRepository tematicaRepo;

    @Mock
    private PreguntaVerdaderoFalsoRepository vfRepo;

    @Mock
    private PreguntaSeleccionUnicaRepository suRepo;

    @Mock
    private PreguntaSeleccionMultipleRepository smRepo;

    @InjectMocks
    private PreguntaServiceImpl service;

    private Tematica tematica;
    private PreguntaVerdaderoFalso vf1;
    private PreguntaVerdaderoFalso vf2;
    private PreguntaSeleccionUnica su1;
    private PreguntaSeleccionUnica su2;
    private PreguntaSeleccionMultiple sm1;

    @BeforeEach
    void setUp() {
        tematica = new Tematica();
        tematica.setId(1L);
        tematica.setNombre("Java");
        tematica.setSlug("java");
        tematica.setDescripcion("Java basics");
        tematica.setColor("blue");
        tematica.setIcono("java-icon");

        vf1 = new PreguntaVerdaderoFalso();
        vf1.setId(1L);
        vf1.setEnunciado("Java es un lenguaje compilado?");
        vf1.setTematica(tematica);
        vf1.setRespuestaCorrecta(true);

        vf2 = new PreguntaVerdaderoFalso();
        vf2.setId(2L);
        vf2.setEnunciado("Java es interpretado?");
        vf2.setTematica(tematica);
        vf2.setRespuestaCorrecta(false);

        su1 = new PreguntaSeleccionUnica();
        su1.setId(3L);
        su1.setEnunciado("Palabra para definir clase?");
        su1.setTematica(tematica);
        su1.setOpciones(List.of("class", "struct", "interface", "enum"));
        su1.setOpcionesCorrectas(List.of("class"));

        su2 = new PreguntaSeleccionUnica();
        su2.setId(4L);
        su2.setEnunciado("Palabra para herencia?");
        su2.setTematica(tematica);
        su2.setOpciones(List.of("extends", "implements", "inherits", "super"));
        su2.setOpcionesCorrectas(List.of("extends"));

        sm1 = new PreguntaSeleccionMultiple();
        sm1.setId(5L);
        sm1.setEnunciado("Tipos primitivos?");
        sm1.setTematica(tematica);
        sm1.setOpciones(List.of("int", "float", "String", "boolean"));
        sm1.setOpcionesCorrectas(new ArrayList<>(List.of("int", "float", "boolean")));
    }

    @Nested
    @DisplayName("filtrarPreguntas")
    class FiltrarPreguntasTests {

        @Test
        @DisplayName("con ambos filtros: tematica y tipo")
        void conTematicaYTipo() {
            Pageable pageable = PageRequest.of(0, 10);
            @SuppressWarnings("unchecked")
            Page<Pregunta> page = new PageImpl<>(List.of(vf1, vf2));
            when(preguntaRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(page);

            Page<Pregunta> result = service.filtrarPreguntas(1L, "VERDADERO_FALSO", pageable);

            assertEquals(2, result.getTotalElements());
            verify(preguntaRepo).findAll(any(Specification.class), eq(pageable));
        }

        @Test
        @DisplayName("solo con filtro de tematica")
        void soloTematica() {
            Pageable pageable = PageRequest.of(0, 10);
            @SuppressWarnings("unchecked")
            Page<Pregunta> page = new PageImpl<>(List.of(vf1, vf2, su1));
            when(preguntaRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(page);

            Page<Pregunta> result = service.filtrarPreguntas(1L, null, pageable);

            assertEquals(3, result.getTotalElements());
            verify(preguntaRepo).findAll(any(Specification.class), eq(pageable));
        }

        @Test
        @DisplayName("solo con filtro de tipo")
        void soloTipo() {
            Pageable pageable = PageRequest.of(0, 10);
            @SuppressWarnings("unchecked")
            Page<Pregunta> page = new PageImpl<>(List.of(su1, su2));
            when(preguntaRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(page);

            Page<Pregunta> result = service.filtrarPreguntas(null, "SELECCION_UNICA", pageable);

            assertEquals(2, result.getTotalElements());
            verify(preguntaRepo).findAll(any(Specification.class), eq(pageable));
        }

        @Test
        @DisplayName("sin filtros")
        void sinFiltros() {
            Pageable pageable = PageRequest.of(0, 10);
            @SuppressWarnings("unchecked")
            Page<Pregunta> page = new PageImpl<>(List.of(vf1, vf2, su1, su2, sm1));
            when(preguntaRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(page);

            Page<Pregunta> result = service.filtrarPreguntas(null, null, pageable);

            assertEquals(5, result.getTotalElements());
            verify(preguntaRepo).findAll(any(Specification.class), eq(pageable));
        }
    }

    @Nested
    @DisplayName("obtenerTematicas")
    class ObtenerTematicasTests {

        @Test
        @DisplayName("devuelve todas las tematicas")
        void devuelveTodas() {
            Tematica t2 = new Tematica();
            t2.setId(2L);
            t2.setNombre("Python");
            when(tematicaRepo.findAll()).thenReturn(List.of(tematica, t2));

            List<Tematica> result = service.obtenerTematicas();

            assertEquals(2, result.size());
            assertEquals("Java", result.get(0).getNombre());
            assertEquals("Python", result.get(1).getNombre());
            verify(tematicaRepo).findAll();
        }

        @Test
        @DisplayName("devuelve lista vacia si no hay tematicas")
        void listaVacia() {
            when(tematicaRepo.findAll()).thenReturn(List.of());

            List<Tematica> result = service.obtenerTematicas();

            assertTrue(result.isEmpty());
            verify(tematicaRepo).findAll();
        }
    }

    @Nested
    @DisplayName("obtenerPreguntasByTematica")
    class ObtenerPreguntasByTematicaTests {

        @Test
        @DisplayName("con tematicaId concreto")
        void conTematicaId() {
            when(preguntaRepo.findByTematicaId(1L)).thenReturn(List.of(vf1, su1));

            List<Pregunta> result = service.obtenerPreguntasByTematica(1L);

            assertEquals(2, result.size());
            verify(preguntaRepo).findByTematicaId(1L);
        }

        @Test
        @DisplayName("con tematicaId null devuelve todas")
        void conTematicaNull() {
            when(preguntaRepo.findAll()).thenReturn(List.of(vf1, vf2, su1, sm1));

            List<Pregunta> result = service.obtenerPreguntasByTematica(null);

            assertEquals(4, result.size());
            verify(preguntaRepo).findAll();
            verify(preguntaRepo, never()).findByTematicaId(any());
        }
    }

    @Nested
    @DisplayName("obtenerPreguntasVF")
    class ObtenerPreguntasVFTests {

        @Test
        @DisplayName("devuelve todas las preguntas de V/F")
        void devuelveTodasVF() {
            when(vfRepo.findAll()).thenReturn(List.of(vf1, vf2));

            List<PreguntaVerdaderoFalso> result = service.obtenerPreguntasVF();

            assertEquals(2, result.size());
            verify(vfRepo).findAll();
        }

        @Test
        @DisplayName("lista vacia si no hay")
        void listaVacia() {
            when(vfRepo.findAll()).thenReturn(List.of());

            List<PreguntaVerdaderoFalso> result = service.obtenerPreguntasVF();

            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("obtenerPreguntasVFByTematica")
    class ObtenerPreguntasVFByTematicaTests {

        @Test
        @DisplayName("filtra por tematica")
        void filtraPorTematica() {
            when(vfRepo.findByTematicaId(1L)).thenReturn(List.of(vf1));

            List<PreguntaVerdaderoFalso> result = service.obtenerPreguntasVFByTematica(1L);

            assertEquals(1, result.size());
            assertEquals(vf1.getEnunciado(), result.get(0).getEnunciado());
            verify(vfRepo).findByTematicaId(1L);
        }
    }

    @Nested
    @DisplayName("obtenerPreguntasSU")
    class ObtenerPreguntasSUTests {

        @Test
        @DisplayName("devuelve todas las preguntas de SU")
        void devuelveTodasSU() {
            when(suRepo.findAll()).thenReturn(List.of(su1, su2));

            List<PreguntaSeleccionUnica> result = service.obtenerPreguntasSU();

            assertEquals(2, result.size());
            verify(suRepo).findAll();
        }
    }

    @Nested
    @DisplayName("obtenerPreguntasSUByTematica")
    class ObtenerPreguntasSUByTematicaTests {

        @Test
        @DisplayName("filtra por tematica")
        void filtraPorTematica() {
            when(suRepo.findByTematicaId(1L)).thenReturn(List.of(su1, su2));

            List<PreguntaSeleccionUnica> result = service.obtenerPreguntasSUByTematica(1L);

            assertEquals(2, result.size());
            verify(suRepo).findByTematicaId(1L);
        }
    }

    @Nested
    @DisplayName("obtenerPreguntasSM")
    class ObtenerPreguntasSMTests {

        @Test
        @DisplayName("devuelve todas las preguntas de SM")
        void devuelveTodasSM() {
            when(smRepo.findAll()).thenReturn(List.of(sm1));

            List<PreguntaSeleccionMultiple> result = service.obtenerPreguntasSM();

            assertEquals(1, result.size());
            verify(smRepo).findAll();
        }
    }

    @Nested
    @DisplayName("obtenerPreguntasSMByTematica")
    class ObtenerPreguntasSMByTematicaTests {

        @Test
        @DisplayName("filtra por tematica")
        void filtraPorTematica() {
            when(smRepo.findByTematicaId(1L)).thenReturn(List.of(sm1));

            List<PreguntaSeleccionMultiple> result = service.obtenerPreguntasSMByTematica(1L);

            assertEquals(1, result.size());
            verify(smRepo).findByTematicaId(1L);
        }
    }

    @Nested
    @DisplayName("evaluarVF")
    class EvaluarVFTests {

        @Test
        @DisplayName("todas correctas")
        void todasCorrectas() {
            when(vfRepo.findAll()).thenReturn(List.of(vf1, vf2));

            Map<String, String> params = new HashMap<>();
            params.put("r_1", "true");
            params.put("r_2", "false");

            ResultadoEvaluacion<Boolean> result = service.evaluarVF(params);

            assertEquals(2, result.puntuacion());
            assertEquals(2, result.total());
            assertTrue(result.resultados().get(1L));
            assertTrue(result.resultados().get(2L));
            assertTrue(result.respuestasUsuario().get(1L));
            assertFalse(result.respuestasUsuario().get(2L));
        }

        @Test
        @DisplayName("todas incorrectas")
        void todasIncorrectas() {
            when(vfRepo.findAll()).thenReturn(List.of(vf1, vf2));

            Map<String, String> params = new HashMap<>();
            params.put("r_1", "false");
            params.put("r_2", "true");

            ResultadoEvaluacion<Boolean> result = service.evaluarVF(params);

            assertEquals(0, result.puntuacion());
            assertEquals(2, result.total());
            assertFalse(result.resultados().get(1L));
            assertFalse(result.resultados().get(2L));
        }

        @Test
        @DisplayName("falta alguna respuesta del usuario")
        void faltaRespuesta() {
            when(vfRepo.findAll()).thenReturn(List.of(vf1, vf2));

            Map<String, String> params = new HashMap<>();
            params.put("r_1", "true");

            ResultadoEvaluacion<Boolean> result = service.evaluarVF(params);

            assertEquals(1, result.puntuacion());
            assertEquals(2, result.total());
            assertTrue(result.resultados().get(1L));
            assertFalse(result.resultados().get(2L));
        }
    }

    @Nested
    @DisplayName("evaluarSU")
    class EvaluarSUTests {

        @Test
        @DisplayName("todas correctas")
        void todasCorrectas() {
            when(suRepo.findAll()).thenReturn(List.of(su1, su2));

            Map<String, String> params = new HashMap<>();
            params.put("r_3", "class");
            params.put("r_4", "extends");

            ResultadoEvaluacion<String> result = service.evaluarSU(params);

            assertEquals(2, result.puntuacion());
            assertEquals(2, result.total());
            assertTrue(result.resultados().get(3L));
            assertTrue(result.resultados().get(4L));
        }

        @Test
        @DisplayName("falla una")
        void fallaUna() {
            when(suRepo.findAll()).thenReturn(List.of(su1, su2));

            Map<String, String> params = new HashMap<>();
            params.put("r_3", "interface");
            params.put("r_4", "extends");

            ResultadoEvaluacion<String> result = service.evaluarSU(params);

            assertEquals(1, result.puntuacion());
            assertFalse(result.resultados().get(3L));
            assertTrue(result.resultados().get(4L));
        }

        @Test
        @DisplayName("sin parametros")
        void sinParametros() {
            when(suRepo.findAll()).thenReturn(List.of(su1, su2));

            ResultadoEvaluacion<String> result = service.evaluarSU(new HashMap<>());

            assertEquals(0, result.puntuacion());
            assertEquals(2, result.total());
            assertFalse(result.resultados().get(3L));
            assertFalse(result.resultados().get(4L));
        }
    }

    @Nested
    @DisplayName("evaluarSM")
    class EvaluarSMTests {

        @Test
        @DisplayName("todas correctas")
        void todasCorrectas() {
            when(smRepo.findAll()).thenReturn(List.of(sm1));

            Map<String, String[]> params = new HashMap<>();
            params.put("r_5", new String[]{"int", "float", "boolean"});

            ResultadoEvaluacion<List<String>> result = service.evaluarSM(params);

            assertEquals(1, result.puntuacion());
            assertEquals(1, result.total());
            assertTrue(result.resultados().get(5L));
        }

        @Test
        @DisplayName("respuesta parcial (faltan opciones)")
        void respuestaParcial() {
            when(smRepo.findAll()).thenReturn(List.of(sm1));

            Map<String, String[]> params = new HashMap<>();
            params.put("r_5", new String[]{"int", "float"});

            ResultadoEvaluacion<List<String>> result = service.evaluarSM(params);

            assertEquals(0, result.puntuacion());
            assertFalse(result.resultados().get(5L));
        }

        @Test
        @DisplayName("respuesta con opciones de mas")
        void respuestaConOpcionesDeMas() {
            when(smRepo.findAll()).thenReturn(List.of(sm1));

            Map<String, String[]> params = new HashMap<>();
            params.put("r_5", new String[]{"int", "float", "boolean", "String"});

            ResultadoEvaluacion<List<String>> result = service.evaluarSM(params);

            assertEquals(0, result.puntuacion());
            assertFalse(result.resultados().get(5L));
        }

        @Test
        @DisplayName("sin respuesta enviada")
        void sinRespuesta() {
            when(smRepo.findAll()).thenReturn(List.of(sm1));

            ResultadoEvaluacion<List<String>> result = service.evaluarSM(new HashMap<>());

            assertEquals(0, result.puntuacion());
            assertFalse(result.resultados().get(5L));
        }
    }

    @Nested
    @DisplayName("guardar")
    class GuardarTests {

        @Test
        @DisplayName("guarda una pregunta y la devuelve")
        void guardaCorrectamente() {
            when(preguntaRepo.save(vf1)).thenReturn(vf1);

            Pregunta result = service.guardar(vf1);

            assertEquals(vf1, result);
            verify(preguntaRepo).save(vf1);
        }
    }

    @Nested
    @DisplayName("obtenerPorId")
    class ObtenerPorIdTests {

        @Test
        @DisplayName("pregunta encontrada")
        void preguntaEncontrada() {
            when(preguntaRepo.findById(1L)).thenReturn(Optional.of(vf1));

            Pregunta result = service.obtenerPorId(1L);

            assertEquals(vf1, result);
            verify(preguntaRepo).findById(1L);
        }

        @Test
        @DisplayName("pregunta no encontrada devuelve null")
        void preguntaNoEncontrada() {
            when(preguntaRepo.findById(99L)).thenReturn(Optional.empty());

            Pregunta result = service.obtenerPorId(99L);

            assertNull(result);
            verify(preguntaRepo).findById(99L);
        }
    }

    @Nested
    @DisplayName("eliminar")
    class EliminarTests {

        @Test
        @DisplayName("elimina por id")
        void eliminaCorrectamente() {
            doNothing().when(preguntaRepo).deleteById(1L);

            service.eliminar(1L);

            verify(preguntaRepo).deleteById(1L);
        }
    }

    @Nested
    @DisplayName("evaluarQuiz")
    class EvaluarQuizTests {

        @Test
        @DisplayName("todas correctas")
        void todasCorrectas() {
            List<Pregunta> preguntas = List.of(vf1, su1, sm1);

            Map<String, String[]> params = new HashMap<>();
            params.put("r_1", new String[]{"true"});
            params.put("r_3", new String[]{"class"});
            params.put("r_5", new String[]{"int", "float", "boolean"});

            ResultadoQuiz result = service.evaluarQuiz(preguntas, params);

            assertEquals(3, result.puntuacion());
            assertEquals(3, result.total());
            assertTrue(result.resultados().get(1L));
            assertTrue(result.resultados().get(3L));
            assertTrue(result.resultados().get(5L));
        }

        @Test
        @DisplayName("todas incorrectas")
        void todasIncorrectas() {
            List<Pregunta> preguntas = List.of(vf1, su1, sm1);

            Map<String, String[]> params = new HashMap<>();
            params.put("r_1", new String[]{"false"});
            params.put("r_3", new String[]{"interface"});
            params.put("r_5", new String[]{"String"});

            ResultadoQuiz result = service.evaluarQuiz(preguntas, params);

            assertEquals(0, result.puntuacion());
            assertEquals(3, result.total());
        }

        @Test
        @DisplayName("mezcla de correctas e incorrectas")
        void mezclaCorrectasIncorrectas() {
            List<Pregunta> preguntas = List.of(vf1, su1, sm1);

            Map<String, String[]> params = new HashMap<>();
            params.put("r_1", new String[]{"true"});
            params.put("r_3", new String[]{"wrong"});
            params.put("r_5", new String[]{"int", "float", "boolean"});

            ResultadoQuiz result = service.evaluarQuiz(preguntas, params);

            assertEquals(2, result.puntuacion());
            assertTrue(result.resultados().get(1L));
            assertFalse(result.resultados().get(3L));
            assertTrue(result.resultados().get(5L));
        }

        @Test
        @DisplayName("respuesta parcial SM cuenta como incorrecta")
        void respuestaParcialSM() {
            List<Pregunta> preguntas = List.of(sm1);

            Map<String, String[]> params = new HashMap<>();
            params.put("r_5", new String[]{"int", "float"});

            ResultadoQuiz result = service.evaluarQuiz(preguntas, params);

            assertEquals(0, result.puntuacion());
            assertFalse(result.resultados().get(5L));
        }

        @Test
        @DisplayName("sin parametros en absoluto")
        void sinParametros() {
            List<Pregunta> preguntas = List.of(vf1, su1);

            ResultadoQuiz result = service.evaluarQuiz(preguntas, new HashMap<>());

            assertEquals(0, result.puntuacion());
            assertEquals(2, result.total());
        }
    }

    @Nested
    @DisplayName("obtenerPreguntasQuiz")
    class ObtenerPreguntasQuizTests {

        @Test
        @DisplayName("con tematicaId: limita a 3 SU + 2 VF + 1 SM")
        void conTematica() {
            PreguntaSeleccionUnica su3 = new PreguntaSeleccionUnica();
            su3.setId(6L);
            su3.setEnunciado("Key word for interface?");
            su3.setTematica(tematica);
            su3.setOpciones(List.of("interface", "class"));
            su3.setOpcionesCorrectas(List.of("interface"));

            PreguntaVerdaderoFalso vf3 = new PreguntaVerdaderoFalso();
            vf3.setId(7L);
            vf3.setEnunciado("JVM stands for Java Virtual Machine?");
            vf3.setTematica(tematica);
            vf3.setRespuestaCorrecta(true);

            when(suRepo.findByTematicaId(1L)).thenReturn(List.of(su1, su2, su3));
            when(vfRepo.findByTematicaId(1L)).thenReturn(List.of(vf1, vf2, vf3));
            when(smRepo.findByTematicaId(1L)).thenReturn(List.of(sm1));

            List<Pregunta> result = service.obtenerPreguntasQuiz(1L);

            assertEquals(6, result.size());
            long suCount = result.stream().filter(p -> p instanceof PreguntaSeleccionUnica).count();
            long vfCount = result.stream().filter(p -> p instanceof PreguntaVerdaderoFalso).count();
            long smCount = result.stream().filter(p -> p instanceof PreguntaSeleccionMultiple).count();
            assertEquals(3, suCount);
            assertEquals(2, vfCount);
            assertEquals(1, smCount);
        }

        @Test
        @DisplayName("sin tematicaId (null): obtiene todas y limita")
        void sinTematica() {
            when(suRepo.findAll()).thenReturn(List.of(su1, su2));
            when(vfRepo.findAll()).thenReturn(List.of(vf1, vf2));
            when(smRepo.findAll()).thenReturn(List.of(sm1));

            List<Pregunta> result = service.obtenerPreguntasQuiz(null);

            assertEquals(5, result.size());
            verify(suRepo).findAll();
            verify(vfRepo).findAll();
            verify(smRepo).findAll();
        }

        @Test
        @DisplayName("cuando no hay preguntas de algun tipo devuelve lo que encuentre")
        void faltanPreguntas() {
            when(suRepo.findByTematicaId(1L)).thenReturn(List.of(su1));
            when(vfRepo.findByTematicaId(1L)).thenReturn(List.of());
            when(smRepo.findByTematicaId(1L)).thenReturn(List.of());

            List<Pregunta> result = service.obtenerPreguntasQuiz(1L);

            assertEquals(1, result.size());
            assertInstanceOf(PreguntaSeleccionUnica.class, result.get(0));
        }
    }

    @Test
    @DisplayName("evaluarVF: puntuacion sin preguntas en repositorio")
    void evaluarVF_sinPreguntas() {
        when(vfRepo.findAll()).thenReturn(List.of());

        ResultadoEvaluacion<Boolean> result = service.evaluarVF(new HashMap<>());

        assertEquals(0, result.puntuacion());
        assertEquals(0, result.total());
        assertTrue(result.resultados().isEmpty());
    }
}
