-- ============================================================
-- Temáticas
-- ============================================================
INSERT INTO tematicas (id, nombre) VALUES (1, 'Historia');
INSERT INTO tematicas (id, nombre) VALUES (2, 'Ciencia');
INSERT INTO tematicas (id, nombre) VALUES (3, 'Geografía');

-- ============================================================
-- Preguntas Verdadero/Falso (5)
-- ============================================================

-- Historia (2)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (1, 'La Revolución Francesa comenzó en 1789.', 1, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (2, 'El Imperio Romano de Occidente cayó en el año 476 d.C.', 1, 'VERDADERO_FALSO', TRUE);

-- Ciencia (2)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (3, 'El agua hierve a 100 grados Celsius a nivel del mar.', 2, 'VERDADERO_FALSO', TRUE);
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (4, 'El Sol es una estrella.', 2, 'VERDADERO_FALSO', TRUE);

-- Geografía (1)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, respuesta_correcta) VALUES (5, 'El Amazonas es el río más largo del mundo.', 3, 'VERDADERO_FALSO', FALSE);

-- ============================================================
-- Preguntas Selección Única (5) + sus opciones
-- ============================================================

-- Historia (2)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (6, '¿Quién fue el primer presidente de los Estados Unidos?', 1, 'SELECCION_UNICA', 'George Washington');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (6, 'George Washington');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (6, 'Thomas Jefferson');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (6, 'Abraham Lincoln');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (6, 'John Adams');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (7, '¿En qué año terminó la Segunda Guerra Mundial?', 1, 'SELECCION_UNICA', '1945');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (7, '1943');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (7, '1944');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (7, '1945');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (7, '1946');

-- Ciencia (1)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (8, '¿Cuál es el elemento químico con símbolo O?', 2, 'SELECCION_UNICA', 'Oxígeno');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (8, 'Oro');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (8, 'Oxígeno');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (8, 'Osmio');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (8, 'Óxido');

-- Geografía (2)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (9, '¿Cuál es la capital de Australia?', 3, 'SELECCION_UNICA', 'Canberra');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (9, 'Sídney');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (9, 'Melbourne');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (9, 'Canberra');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (9, 'Brisbane');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta, opcion_correcta) VALUES (10, '¿Cuál es el país más grande del mundo por superficie?', 3, 'SELECCION_UNICA', 'Rusia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (10, 'China');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (10, 'Estados Unidos');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (10, 'Rusia');
INSERT INTO opciones_seleccion_unica (pregunta_id, opcion) VALUES (10, 'Canadá');

-- ============================================================
-- Preguntas Selección Múltiple (5) + opciones + respuestas
-- ============================================================

-- Historia (1)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (11, '¿Cuáles de las siguientes civilizaciones fueron mesoamericanas?', 1, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (11, 'Maya');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (11, 'Azteca');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (11, 'Inca');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (11, 'Egipcia');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (11, 'Griega');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (11, 'Maya');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (11, 'Azteca');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (11, 'Inca');

-- Ciencia (2)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (12, '¿Cuáles de los siguientes son planetas del Sistema Solar?', 2, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (12, 'Tierra');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (12, 'Marte');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (12, 'Plutón');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (12, 'Luna');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (12, 'Venus');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (12, 'Tierra');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (12, 'Marte');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (12, 'Venus');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (13, '¿Cuáles de los siguientes son estados de la materia?', 2, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (13, 'Sólido');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (13, 'Líquido');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (13, 'Gaseoso');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (13, 'Energía');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (13, 'Plasma');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (13, 'Sólido');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (13, 'Líquido');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (13, 'Gaseoso');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (13, 'Plasma');

-- Geografía (2)
INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (14, '¿Cuáles de los siguientes países tienen costa en el Mar Mediterráneo?', 3, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (14, 'España');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (14, 'Italia');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (14, 'Alemania');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (14, 'Grecia');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (14, 'Suiza');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (14, 'España');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (14, 'Italia');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (14, 'Grecia');

INSERT INTO preguntas (id, enunciado, tematica_id, tipo_pregunta) VALUES (15, '¿Cuáles de los siguientes son continentes?', 3, 'SELECCION_MULTIPLE');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (15, 'África');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (15, 'Europa');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (15, 'Rusia');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (15, 'Oceanía');
INSERT INTO opciones_seleccion_multiple (pregunta_id, opcion) VALUES (15, 'Medio Oriente');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (15, 'África');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (15, 'Europa');
INSERT INTO respuestas_correctas_multiple (pregunta_id, respuesta_correcta) VALUES (15, 'Oceanía');

-- Reset identity sequences for future inserts
ALTER TABLE tematicas ALTER COLUMN id RESTART WITH 4;
ALTER TABLE preguntas ALTER COLUMN id RESTART WITH 16;
