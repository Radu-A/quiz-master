-- ============================================================
-- Temáticas
-- ============================================================
INSERT INTO tematicas (id, nombre, slug, descripcion, color, icono) VALUES (1, 'Cibercultura y Frikismo', 'cibercultura-y-frikismo', 'Desde las entrañas del código y la historia hacker hasta los secretos mejor guardados del cine, la literatura y los videojuegos.', '#3b82f6', 'code');
INSERT INTO tematicas (id, nombre, slug, descripcion, color, icono) VALUES (2, 'Biología Extrema', 'biologia-extrema', 'Criaturas indestructibles, mecanismos de defensa letales, venenos exóticos y rarezas de la flora y fauna mundial.', '#10b981', 'leaf');
INSERT INTO tematicas (id, nombre, slug, descripcion, color, icono) VALUES (3, 'Paradojas Cósmicas', 'paradojas-cosmicas', 'Viajes en el tiempo, física cuántica, agujeros negros y experimentos mentales que desafían la lógica humana.', '#8b5cf6', 'orbit');
INSERT INTO tematicas (id, nombre, slug, descripcion, color, icono) VALUES (4, 'Legalmente Absurdo', 'legalmente-absurdo', 'Normas vigentes, prohibiciones extrañas y leyes históricas de distintos países que parecen una auténtica broma.', '#f59e0b', 'scale');
INSERT INTO tematicas (id, nombre, slug, descripcion, color, icono) VALUES (5, 'Manual de Supervivencia', 'manual-de-supervivencia', 'Qué hacer en situaciones límite, cómo sobrevivir a la naturaleza salvaje y los peligros ocultos en la gastronomía extrema.', '#f43f5e', 'compass');

-- ============================================================
-- Preguntas Verdadero/Falso (25)
-- ============================================================

-- Cibercultura y Frikismo (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (1, 'El primer virus informático fue creado como experimento académico en 1983.', 1, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (2, 'El término "hacker" originalmente tenía connotaciones negativas.', 1, 'VERDADERO_FALSO', FALSE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (3, 'La película Blade Runner está basada en una novela de Philip K. Dick.', 1, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (4, 'El lenguaje de programación Python fue nombrado así por la serpiente pitón.', 1, 'VERDADERO_FALSO', FALSE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (5, 'El primer videojuego comercial fue Pong de Atari.', 1, 'VERDADERO_FALSO', FALSE);

-- Biología Extrema (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (6, 'El tardígrado puede sobrevivir en el vacío del espacio exterior.', 2, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (7, 'La medusa Turritopsis dohrnii es biológicamente inmortal.', 2, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (8, 'El ornitorrinco es un mamífero que pone huevos.', 2, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (9, 'La rana dardo dorada es el animal más venenoso del planeta.', 2, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (10, 'Los tiburones son inmunes al cáncer.', 2, 'VERDADERO_FALSO', FALSE);

-- Paradojas Cósmicas (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (11, 'Según la relatividad especial, el tiempo pasa más lento a mayor velocidad.', 3, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (12, 'Un agujero negro puede evaporarse con el tiempo según la radiación de Hawking.', 3, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (13, 'La paradoja de Fermi plantea por qué no hemos encontrado vida extraterrestre.', 3, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (14, 'El gato de Schrödinger está vivo y muerto al mismo tiempo en la realidad.', 3, 'VERDADERO_FALSO', FALSE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (15, 'La velocidad de la luz puede ser superada por partículas con masa.', 3, 'VERDADERO_FALSO', FALSE);

-- Legalmente Absurdo (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (16, 'En Singapur está prohibido masticar chicle.', 4, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (17, 'En Francia es ilegal llamar "Napoleón" a un cerdo.', 4, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (18, 'En Australia es obligatorio llevar un bozal para los cerdos en la vía pública.', 4, 'VERDADERO_FALSO', FALSE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (19, 'En Tailandia es ilegal pisar un billete porque lleva la imagen del rey.', 4, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (20, 'En Canadá está prohibido pagar solo con monedas de un centavo.', 4, 'VERDADERO_FALSO', FALSE);

-- Manual de Supervivencia (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (21, 'Beber agua de mar es seguro en pequeñas cantidades si estás deshidratado.', 5, 'VERDADERO_FALSO', FALSE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (22, 'Si te pierdes en el bosque, lo mejor es seguir un río corriente abajo.', 5, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (23, 'El musgo siempre crece en el lado norte de los árboles.', 5, 'VERDADERO_FALSO', FALSE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (24, 'Se puede sobrevivir hasta tres semanas sin comida pero solo tres días sin agua.', 5, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (25, 'Chupar el veneno de una mordedura de serpiente es un método efectivo de primeros auxilios.', 5, 'VERDADERO_FALSO', FALSE);

-- ============================================================
-- Preguntas Selección Única (25) + sus opciones
-- ============================================================

-- Cibercultura y Frikismo (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (26, '¿Quién es considerado el padre de la computación teórica?', 1, 'SELECCION_UNICA', 'Alan Turing');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (26, 'Alan Turing');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (26, 'Charles Babbage');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (26, 'John von Neumann');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (26, 'Grace Hopper');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (27, '¿En qué año se lanzó el primer iPhone?', 1, 'SELECCION_UNICA', '2007');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (27, '2005');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (27, '2006');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (27, '2007');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (27, '2008');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (28, '¿Cuál es la saga literaria de ciencia ficción más vendida de la historia?', 1, 'SELECCION_UNICA', 'Dune');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (28, 'Star Wars');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (28, 'Dune');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (28, 'Fundación');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (28, 'El juego de Ender');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (29, '¿Qué empresa desarrolló el sistema operativo Linux?', 1, 'SELECCION_UNICA', 'Ninguna, es un proyecto comunitario');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (29, 'Microsoft');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (29, 'IBM');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (29, 'Ninguna, es un proyecto comunitario');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (29, 'Canonical');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (30, '¿Cuál fue el primer videojuego en incluir un código Konami?', 1, 'SELECCION_UNICA', 'Gradius');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (30, 'Contra');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (30, 'Gradius');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (30, 'Metal Gear');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (30, 'Castlevania');

-- Biología Extrema (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (31, '¿Cuál es el animal más grande que ha existido en la Tierra?', 2, 'SELECCION_UNICA', 'Ballena azul');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (31, 'Tyrannosaurus Rex');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (31, 'Ballena azul');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (31, 'Megalodon');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (31, 'Argentinosaurio');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (32, '¿Qué animal tiene la mordida más fuerte del reino animal?', 2, 'SELECCION_UNICA', 'Cocodrilo de agua salada');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (32, 'Tiburón blanco');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (32, 'Cocodrilo de agua salada');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (32, 'Hipopótamo');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (32, 'León');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (33, '¿Qué planta es conocida por ser la más rápida del mundo?', 2, 'SELECCION_UNICA', 'Bambú');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (33, 'Bambú');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (33, 'Eucalipto');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (33, 'Secuoya');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (33, 'Caña de azúcar');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (34, '¿Cuántos corazones tiene un pulpo?', 2, 'SELECCION_UNICA', 'Tres');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (34, 'Uno');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (34, 'Dos');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (34, 'Tres');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (34, 'Cuatro');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (35, '¿Qué animal puede regenerar partes de su cerebro?', 2, 'SELECCION_UNICA', 'Ajolote');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (35, 'Estrella de mar');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (35, 'Ajolote');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (35, 'Salamandra');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (35, 'Lagartija');

-- Paradojas Cósmicas (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (36, '¿Qué científico formuló la teoría de la relatividad general?', 3, 'SELECCION_UNICA', 'Albert Einstein');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (36, 'Niels Bohr');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (36, 'Albert Einstein');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (36, 'Isaac Newton');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (36, 'Stephen Hawking');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (37, '¿Qué es un agujero de gusano?', 3, 'SELECCION_UNICA', 'Un atajo teórico en el espacio-tiempo');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (37, 'Un tipo de agujero negro');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (37, 'Un atajo teórico en el espacio-tiempo');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (37, 'Una estrella colapsada');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (37, 'Una galaxia enana');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (38, '¿Qué partícula subatómica fue confirmada en 2012 por el CERN?', 3, 'SELECCION_UNICA', 'Bosón de Higgs');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (38, 'Neutrino estéril');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (38, 'Bosón de Higgs');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (38, 'Quark top');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (38, 'Gravitón');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (39, '¿Qué es la materia oscura?', 3, 'SELECCION_UNICA', 'Materia invisible que ejerce gravedad');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (39, 'Antimateria');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (39, 'Materia invisible que ejerce gravedad');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (39, 'Agujeros negros pequeños');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (39, 'Gas interestelar frío');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (40, '¿Cuál es la paradoja que plantea viajar al pasado y matar a tu abuelo?', 3, 'SELECCION_UNICA', 'Paradoja del abuelo');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (40, 'Paradoja del abuelo');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (40, 'Paradoja de Bootstrap');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (40, 'Paradoja de los gemelos');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (40, 'Paradoja de Newcomb');

-- Legalmente Absurdo (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (41, '¿En qué país está prohibido mascar chicle en público?', 4, 'SELECCION_UNICA', 'Singapur');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (41, 'Japón');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (41, 'Singapur');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (41, 'Corea del Sur');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (41, 'Malasia');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (42, '¿En qué país es ilegal tener un solo pez dorado porque se considera crueldad animal?', 4, 'SELECCION_UNICA', 'Italia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (42, 'Italia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (42, 'Reino Unido');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (42, 'Noruega');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (42, 'Alemania');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (43, '¿En qué país es obligatorio llevar chocolate de emergencia en el coche?', 4, 'SELECCION_UNICA', 'No existe tal ley');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (43, 'Suiza');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (43, 'Bélgica');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (43, 'No existe tal ley');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (43, 'Alemania');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (44, '¿En qué país está prohibido llevar tacones en edificios históricos?', 4, 'SELECCION_UNICA', 'Grecia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (44, 'Italia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (44, 'Grecia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (44, 'Francia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (44, 'España');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (45, '¿En qué país es ilegal nombrar a tu hijo "Batman"?', 4, 'SELECCION_UNICA', 'Malasia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (45, 'Estados Unidos');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (45, 'Malasia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (45, 'China');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (45, 'Arabia Saudita');

-- Manual de Supervivencia (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (46, '¿Cuál es la regla de treses en supervivencia?', 5, 'SELECCION_UNICA', '3 min sin aire, 3 días sin agua, 3 semanas sin comida');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (46, '3 horas sin refugio, 3 días sin agua, 3 semanas sin comida');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (46, '3 min sin aire, 3 días sin agua, 3 semanas sin comida');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (46, '3 minutos sin agua, 3 horas sin comida, 3 días sin refugio');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (46, '3 días sin aire, 3 semanas sin agua, 3 meses sin comida');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (47, '¿Qué señal internacional de socorro se usa en la naturaleza?', 5, 'SELECCION_UNICA', 'Tres señales iguales (SOS)');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (47, 'Una hogera grande');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (47, 'Tres señales iguales (SOS)');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (47, 'Gritar "ayuda" tres veces');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (47, 'Una bandera roja');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (48, '¿Qué hacer si te encuentras con un oso en el bosque?', 5, 'SELECCION_UNICA', 'Hacerte el muerto si es pardo, defenderse si es negro');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (48, 'Correr lo más rápido posible');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (48, 'Hacerte el muerto si es pardo, defenderse si es negro');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (48, 'Trepar a un árbol');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (48, 'Gritar para asustarlo');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (49, '¿Cuál es el método más fiable para purificar agua en la naturaleza?', 5, 'SELECCION_UNICA', 'Hervirla durante al menos 1 minuto');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (49, 'Filtrarla con ropa');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (49, 'Añadir sal');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (49, 'Hervirla durante al menos 1 minuto');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (49, 'Dejarla reposar 24 horas');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (50, '¿Qué planta es comestible universalmente reconocible?', 5, 'SELECCION_UNICA', 'Diente de león');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (50, 'Hiedra venenosa');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (50, 'Diente de león');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (50, 'Belladona');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (50, 'Amanita muscaria');

-- ============================================================
-- Preguntas Selección Múltiple (25) + opciones + respuestas
-- ============================================================

-- Cibercultura y Frikismo (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (51, '¿Cuáles de los siguientes son lenguajes de programación?', 1, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (51, 'Python');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (51, 'Java');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (51, 'HTML');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (51, 'Rust');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (51, 'Photoshop');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (51, 'Python');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (51, 'Java');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (51, 'Rust');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (52, '¿Cuáles de estas películas fueron dirigidas por Christopher Nolan?', 1, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (52, 'Inception');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (52, 'Interstellar');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (52, 'Matrix');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (52, 'The Dark Knight');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (52, 'Blade Runner');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (52, 'Inception');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (52, 'Interstellar');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (52, 'The Dark Knight');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (53, '¿Cuáles de estos personajes pertenecen al universo de Star Wars?', 1, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (53, 'Luke Skywalker');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (53, 'Darth Vader');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (53, 'Spock');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (53, 'Han Solo');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (53, 'Jean-Luc Picard');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (53, 'Luke Skywalker');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (53, 'Darth Vader');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (53, 'Han Solo');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (54, '¿Cuáles de estos son protocolos de internet?', 1, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (54, 'HTTP');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (54, 'FTP');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (54, 'TCP');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (54, 'CSS');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (54, 'USB');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (54, 'HTTP');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (54, 'FTP');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (54, 'TCP');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (55, '¿Cuáles de estos videojuegos fueron desarrollados por Nintendo?', 1, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (55, 'Super Mario Bros');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (55, 'The Legend of Zelda');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (55, 'Sonic the Hedgehog');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (55, 'Pokémon');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (55, 'Final Fantasy');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (55, 'Super Mario Bros');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (55, 'The Legend of Zelda');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (55, 'Pokémon');

-- Biología Extrema (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (56, '¿Cuáles de estos animales son venenosos?', 2, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (56, 'Rana dardo dorada');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (56, 'Pulpo de anillos azules');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (56, 'Delfín');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (56, 'Serpiente taipán');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (56, 'Koala');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (56, 'Rana dardo dorada');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (56, 'Pulpo de anillos azules');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (56, 'Serpiente taipán');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (57, '¿Cuáles de estos animales pueden regenerar extremidades?', 2, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (57, 'Estrella de mar');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (57, 'Ajolote');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (57, 'Lagartija');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (57, 'Perro');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (57, 'Águila');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (57, 'Estrella de mar');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (57, 'Ajolote');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (57, 'Lagartija');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (58, '¿Cuáles de estos son mecanismos de defensa animal?', 2, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (58, 'Mimetismo');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (58, 'Autotomía');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (58, 'Aposematismo');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (58, 'Fotosíntesis');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (58, 'Hibernación');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (58, 'Mimetismo');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (58, 'Autotomía');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (58, 'Aposematismo');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (59, '¿Cuáles de estos organismos pueden vivir en condiciones extremas?', 2, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (59, 'Tardígrado');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (59, 'Bacteria termófila');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (59, 'Gato doméstico');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (59, 'Arquea halófila');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (59, 'Rosa');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (59, 'Tardígrado');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (59, 'Bacteria termófila');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (59, 'Arquea halófila');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (60, '¿Cuáles de estos animales tienen sangre azul?', 2, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (60, 'Pulpo');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (60, 'Cangrejo herradura');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (60, 'Tiburón');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (60, 'Caracol');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (60, 'Serpiente');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (60, 'Pulpo');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (60, 'Cangrejo herradura');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (60, 'Caracol');

-- Paradojas Cósmicas (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (61, '¿Cuáles de estos son tipos de radiación electromagnética?', 3, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (61, 'Rayos gamma');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (61, 'Ultravioleta');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (61, 'Sonido');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (61, 'Infrarrojo');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (61, 'Gravedad');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (61, 'Rayos gamma');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (61, 'Ultravioleta');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (61, 'Infrarrojo');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (62, '¿Cuáles de estas son partículas elementales del Modelo Estándar?', 3, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (62, 'Quark');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (62, 'Electrón');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (62, 'Protón');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (62, 'Neutrino');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (62, 'Átomo');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (62, 'Quark');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (62, 'Electrón');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (62, 'Neutrino');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (63, '¿Cuáles de estos conceptos están relacionados con la mecánica cuántica?', 3, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (63, 'Entrelazamiento');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (63, 'Superposición');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (63, 'Gravedad newtoniana');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (63, 'Principio de incertidumbre');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (63, 'Relatividad general');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (63, 'Entrelazamiento');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (63, 'Superposición');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (63, 'Principio de incertidumbre');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (64, '¿Cuáles de estos son fenómenos astronómicos reales?', 3, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (64, 'Supernova');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (64, 'Púlsar');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (64, 'Agujero de gusano confirmado');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (64, 'Estrella de neutrones');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (64, 'Magnetar');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (64, 'Supernova');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (64, 'Púlsar');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (64, 'Estrella de neutrones');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (64, 'Magnetar');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (65, '¿Cuáles de estos científicos contribuyeron a la física cuántica?', 3, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (65, 'Max Planck');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (65, 'Werner Heisenberg');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (65, 'Galileo Galilei');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (65, 'Erwin Schrödinger');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (65, 'Aristóteles');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (65, 'Max Planck');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (65, 'Werner Heisenberg');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (65, 'Erwin Schrödinger');

-- Legalmente Absurdo (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (66, '¿Cuáles de estas leyes absurdas se atribuyen a países reales?', 4, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (66, 'Prohibido morir en Longyearbyen, Noruega');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (66, 'Prohibido llevar tacones en la Acrópolis, Grecia');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (66, 'Prohibido ser triste en Bután');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (66, 'Prohibido volar los domingos en Japón');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (66, 'Prohibido usar pijamas en público en Alabama, EE.UU.');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (66, 'Prohibido morir en Longyearbyen, Noruega');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (66, 'Prohibido llevar tacones en la Acrópolis, Grecia');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (66, 'Prohibido usar pijamas en público en Alabama, EE.UU.');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (67, '¿Cuáles de estos países tienen prohibiciones sobre el chicle?', 4, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (67, 'Singapur');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (67, 'Japón');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (67, 'Corea del Norte');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (67, 'Tailandia');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (67, 'Brasil');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (67, 'Singapur');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (68, '¿Cuáles de estas son leyes reales en Suiza?', 4, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (68, 'No se puede tirar la cadena después de las 10 PM en apartamentos');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (68, 'Está prohibido tener un solo conejo (son animales sociales)');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (68, 'No se puede lavar el coche los domingos');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (68, 'Está prohibido usar tacones en casas de madera');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (68, 'No se puede hacer ruido los domingos');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (68, 'Está prohibido tener un solo conejo (son animales sociales)');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (68, 'No se puede lavar el coche los domingos');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (68, 'No se puede hacer ruido los domingos');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (69, '¿En cuáles de estos países es ilegal llevar el rostro cubierto en público?', 4, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (69, 'Francia');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (69, 'Bélgica');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (69, 'Canadá');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (69, 'Dinamarca');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (69, 'Portugal');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (69, 'Francia');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (69, 'Bélgica');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (69, 'Dinamarca');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (70, '¿Cuáles de estas prohibiciones existen en Australia?', 4, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (70, 'No cambiar bombillas si no eres electricista certificado');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (70, 'No se puede caminar desnudo por casa');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (70, 'Prohibido llevar pantalones cortos al parlamento');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (70, 'No se puede conducir con chanclas');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (70, 'Prohibido nombrar a tu hijo "Real"');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (70, 'No cambiar bombillas si no eres electricista certificado');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (70, 'Prohibido nombrar a tu hijo "Real"');

-- Manual de Supervivencia (5)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (71, '¿Cuáles de estos son métodos válidos para orientarse sin brújula?', 5, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (71, 'Usar las estrellas del hemisferio norte');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (71, 'Observar la posición del sol');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (71, 'Seguir el GPS del móvil siempre');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (71, 'Usar un reloj analógico y el sol');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (71, 'Seguir siempre hacia el norte');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (71, 'Usar las estrellas del hemisferio norte');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (71, 'Observar la posición del sol');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (71, 'Usar un reloj analógico y el sol');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (72, '¿Cuáles de estos alimentos silvestres son generalmente comestibles?', 5, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (72, 'Moras');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (72, 'Diente de león');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (72, 'Champiñones desconocidos');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (72, 'Trébol');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (72, 'Bayas blancas desconocidas');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (72, 'Moras');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (72, 'Diente de león');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (72, 'Trébol');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (73, '¿Cuáles de estos elementos son esenciales en un kit de supervivencia?', 5, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (73, 'Cuchillo multiusos');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (73, 'Mechero o cerillas impermeables');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (73, 'Videoconsola portátil');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (73, 'Botiquín básico');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (73, 'Manta térmica');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (73, 'Cuchillo multiusos');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (73, 'Mechero o cerillas impermeables');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (73, 'Botiquín básico');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (73, 'Manta térmica');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (74, '¿Cuáles de estos son signos de hipotermia?', 5, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (74, 'Temblor incontrolable');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (74, 'Confusión mental');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (74, 'Sudoración excesiva');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (74, 'Habla arrastrada');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (74, 'Piel caliente y roja');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (74, 'Temblor incontrolable');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (74, 'Confusión mental');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (74, 'Habla arrastrada');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (75, '¿Cuáles de estas son reglas básicas ante un incendio forestal?', 5, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (75, 'Alejarse en dirección contraria al viento');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (75, 'Buscar zonas ya quemadas como refugio');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (75, 'Correr cuesta arriba');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (75, 'Cubrirse la boca con un trapo húmedo');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (75, 'Esconderse en un vehículo cerrado');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (75, 'Alejarse en dirección contraria al viento');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (75, 'Buscar zonas ya quemadas como refugio');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (75, 'Cubrirse la boca con un trapo húmedo');

-- Reset identity sequences for future inserts
ALTER TABLE tematicas ALTER COLUMN id RESTART WITH 6;
ALTER TABLE preguntas ALTER COLUMN id RESTART WITH 76;
