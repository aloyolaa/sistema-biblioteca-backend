INSERT INTO autor (nombre, apellido) VALUES ('NO', ' ESPECIFICA')
INSERT INTO autor (nombre, apellido) VALUES ('MINISTERIO', ' DE EDUCACIÓN')
INSERT INTO autor (nombre, apellido) VALUES ('JULIO', 'VERNE')
INSERT INTO autor (nombre, apellido) VALUES ('WILSON', 'URTECHO RODRIGUEZ')
INSERT INTO autor (nombre, apellido) VALUES ('SYLVIANO', 'ROCHE')
INSERT INTO autor (nombre, apellido) VALUES ('MANUEL', 'GONZALES PRADA')

INSERT INTO categoria (nombre) VALUES ('NO ESPECIFICA')
INSERT INTO categoria (nombre) VALUES ('LIBRO')
INSERT INTO categoria (nombre) VALUES ('OBRA')
INSERT INTO categoria (nombre) VALUES ('GUIA DE APRENDIZAJE')
INSERT INTO categoria (nombre) VALUES ('SESIÓN DE APRENDIZAJE')

INSERT INTO editorial (nombre) VALUES ('NO ESPECIFICA')
INSERT INTO editorial (nombre) VALUES ('SANTILLANA')
INSERT INTO editorial (nombre) VALUES ('SAN MARCOS')
INSERT INTO editorial (nombre) VALUES ('SAN LUIS')
INSERT INTO editorial (nombre) VALUES ('SAN LUCAS')

INSERT INTO area (nombre) VALUES ('NO ESPECIFICA');
INSERT INTO area (nombre) VALUES ('MATEMÁTICA');
INSERT INTO area (nombre) VALUES ('COMUNICACIÓN');
INSERT INTO area (nombre) VALUES ('PERSONAL SOCIAL');
INSERT INTO area (nombre) VALUES ('CIENCIA Y AMBIENTE');
INSERT INTO area (nombre) VALUES ('TODAS');

INSERT INTO docente (nombre, apellido, dni, telefono) VALUES ('EDWIN', 'CABRERA', '18293123', '99988877')
INSERT INTO docente (nombre, apellido, dni, telefono) VALUES ('BRYAN', 'SANCHEZ', '18291029', '99988879')
INSERT INTO docente (nombre, apellido, dni, telefono) VALUES ('VERONICA', 'GUTIERREZ', '19028323', '99988870')
INSERT INTO docente (nombre, apellido, dni, telefono) VALUES ('FATIMA', 'LOPEZ', '19034921', '99988872')
INSERT INTO docente (nombre, apellido, dni, telefono) VALUES ('LUIS', 'MARQUEZ', '19218283', '99988873')
INSERT INTO docente (nombre, apellido, dni, telefono) VALUES ('ALAN', 'LOYOLA', '19232032', '99988874')

INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('MI', 'MATEMÁTICA I', 2018, 1, 2, 2, 2)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('MII', 'MATEMÁTICA II', 2019, 2, 2, 2, 2)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('CIII', 'COMUNICACIÓN III', 2020, 3, 3, 3, 3)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('CIV', 'COMUNICACIÓN IV', 2020, 4, 2, 3, 3)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('PSV', 'PERSONAL SOCIAL V', 2022, 5, 3, 4, 4)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('CAVI', 'CIENCIA Y AMBIENTE VI', 2021, 6, 4, 5, 5)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('LV', 'LA VUELTA AL MUNDO EN OCHENTA DIAS', 2000, 1, 2, 3, 3)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('GAI', 'GUIA DE APRENDIZAJE I', 2023, 1, 5, 1, 6)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('SAI', 'SESIONES DE APRENDIZAJE I', 2023, 1, 5, 1, 6)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('GAII', 'GUIA DE APRENDIZAJE II', 2023, 2, 5, 1, 6)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('SAII', 'SESIONES DE APRENDIZAJE II', 2022, 2, 5, 1, 6)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('ATI', 'ALGO TE IDENTIFICA', 2007, 4, 2, 3, 3)
INSERT INTO libro (codigo, titulo, anio, grado, categoria_id, editorial_id, area_id) VALUES ('CET', 'CREO EN TI', 2005, 5, 2, 3, 3)

INSERT INTO libro_autor (libro_id, autor_id) VALUES (1, 1)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (1, 3)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (2, 1)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (3, 2)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (4, 3)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (5, 4)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (6, 5)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (7, 6)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (8, 1)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (9, 2)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (10, 3)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (11, 4)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (12, 5)
INSERT INTO libro_autor (libro_id, autor_id) VALUES (13, 6)

INSERT INTO material (codigo, nombre, medidas, area_id) VALUES ('JM', 'JARRAS MEDIDORAS', '100x200x20', 1)
INSERT INTO material (codigo, nombre, area_id) VALUES ('DM', 'DADOS NUMÉRICOS', 1)
INSERT INTO material (codigo, nombre, area_id) VALUES ('CA', 'CALCULADORAS', 1)
INSERT INTO material (codigo, nombre, area_id) VALUES ('DA', 'DADOS ARITMETICOS', 1)
INSERT INTO material (codigo, nombre, area_id) VALUES ('CB', 'MATERIAL BASE', 1)
INSERT INTO material (codigo, nombre, area_id) VALUES ('ES', 'ESQUELETO', 1)
INSERT INTO material (codigo, nombre, area_id) VALUES ('FDTL', 'FICHAS DE DIFERENTES TIPOS DE LETRAS', 2)
INSERT INTO material (codigo, nombre, area_id) VALUES ('IM', 'IMANES', 4)
INSERT INTO material (codigo, nombre, area_id) VALUES ('LU', 'LUPAS', 4)

INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (1, 'BUENO', true, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (2, 'BUENO', true, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (3, 'MALO', false, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (4, 'MALO', false, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (5, 'MALO', false, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (6, 'BUENO', true, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (7, 'BUENO', true, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (8, 'BUENO', true, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (9, 'MALO', false, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (10, 'BUENO', false, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (11, 'BUENO', false, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (12, 'REGULAR', false, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (13, 'BUENO', false, '', 1)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (1, 'BUENO', true, '', 2)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (2, 'BUENO', true, '', 2)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (3, 'REGULAR', true, '', 2)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (4, 'REGULAR', false, '', 2)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (5, 'BUENO', false, '', 2)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (6, 'BUENO', false, '', 2)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (7, 'BUENO', false, '', 2)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (8, 'BUENO', false, '', 2)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (1, 'BUENO', false, '', 3)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (2, 'BUENO', false, '', 3)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (3, 'BUENO', false, '', 3)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (4, 'BUENO', false, '', 3)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (1, 'REGULAR', false, '', 4)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (2, 'MALO', false, '', 4)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (1, 'MALO', false, '', 5)
INSERT INTO ejemplar_libro (numero, estado, prestado, observaciones, libro_id) VALUES (2, 'MALO', false, '', 5)

INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (1, 'BUENO', true, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (2, 'BUENO', true, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (3, 'BUENO', true, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (4, 'BUENO', false, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (5, 'MALO', false, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (6, 'BUENO', false, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (7, 'BUENO', true, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (8, 'BUENO', true, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (9, 'BUENO', false, '', 1)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (1, 'BUENO', false, '', 2)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (2, 'BUENO', false, '', 2)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (3, 'REGULAR', false, '', 2)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (4, 'REGULAR', false, '', 2)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (5, 'MALO', false, '', 2)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (6, 'MALO', false, '', 2)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (7, 'MALO', false, '', 2)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (1, 'BUENO', true, '', 3)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (1, 'REGULAR', true, '', 4)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (1, 'BUENO', false, '', 5)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (2, 'MALO', false, '', 5)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (1, 'REGULAR', false, '', 6)
INSERT INTO ejemplar_material (numero, estado, prestado, observaciones, material_id) VALUES (2, 'MALO', false, '', 6)

INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-12 12:00:00', '2 MI', 1, 'A', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-13 11:30:00', '3 MII', 2, 'B', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-15 23:59:59', '4 M', 3, 'C', true, '', 2)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-12 12:00:00', 'Descripcion 1', 1, 'A', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-13 11:30:00', 'Descripcion 2', 2, 'B', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-15 23:59:59', 'Descripcion 3', 3, 'C', true, '', 2)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-12 12:00:00', 'Descripcion 1', 1, 'A', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-13 11:30:00', 'Descripcion 2', 2, 'B', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-15 23:59:59', 'Descripcion 3', 3, 'C', true, '', 2)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-16 12:00:00', 'Descripcion 1', 1, 'A', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-17 11:30:00', 'Descripcion 2', 2, 'B', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-18 23:59:59', 'Descripcion 3', 3, 'C', true, '', 2)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-16 12:00:00', 'Descripcion 1', 1, 'A', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-17 11:30:00', 'Descripcion 2', 2, 'B', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-18 23:59:59', 'Descripcion 3', 3, 'C', true, '', 2)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-16 12:00:00', 'Descripcion 1', 1, 'A', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-17 11:30:00', 'Descripcion 2', 2, 'B', true, '', 1)
INSERT INTO prestamo_libro (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-18 23:59:59', 'Descripcion 3', 3, 'C', true, '', 2)

INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-12 12:00:00', 'Esqueleto', 1, 'A', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-13 11:30:00', 'Lamina de los Musculos', 2, 'B', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-15 19:30:00', 'Lamina del Sistema respiratorio', 3, 'C', true, '', 4)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-12 12:00:00', 'Esqueleto', 1, 'A', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-13 11:30:00', 'Esqueleto', 2, 'B', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-15 19:30:00', 'Lamina de los Musculos', 3, 'C', true, '', 4)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-12 12:00:00', 'Calculadoras', 1, 'A', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-13 11:30:00', 'Descripcion 2', 2, 'B', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-15 19:30:00', 'Descripcion 3', 3, 'C', true, '', 4)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-16 12:00:00', 'Descripcion 1', 1, 'A', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-17 11:30:00', 'Descripcion 2', 2, 'B', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-18 19:30:00', 'Descripcion 3', 3, 'C', true, '', 4)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-16 12:00:00', 'Descripcion 1', 1, 'A', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-17 11:30:00', 'Descripcion 2', 2, 'B', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-18 19:30:00', 'Descripcion 3', 3, 'C', true, '', 4)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-16 12:00:00', 'Descripcion 1', 1, 'A', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-17 11:30:00', 'Descripcion 2', 2, 'B', true, '', 3)
INSERT INTO prestamo_material (fecha_prestamo, descripcion, grado, seccion, activo, observaciones, docente_id) VALUES ('2023-03-18 19:30:00', 'Descripcion 3', 3, 'C', true, '', 4)

INSERT INTO detalle_prestamo_libro (ejemplar_libro_id, prestamo_libro_id) VALUES (1, 1)
INSERT INTO detalle_prestamo_libro (ejemplar_libro_id, prestamo_libro_id) VALUES (2, 1)
INSERT INTO detalle_prestamo_libro (ejemplar_libro_id, prestamo_libro_id) VALUES (6, 1)
INSERT INTO detalle_prestamo_libro (ejemplar_libro_id, prestamo_libro_id) VALUES (7, 2)
INSERT INTO detalle_prestamo_libro (ejemplar_libro_id, prestamo_libro_id) VALUES (8, 2)
INSERT INTO detalle_prestamo_libro (ejemplar_libro_id, prestamo_libro_id) VALUES (14, 3)
INSERT INTO detalle_prestamo_libro (ejemplar_libro_id, prestamo_libro_id) VALUES (15, 3)
INSERT INTO detalle_prestamo_libro (ejemplar_libro_id, prestamo_libro_id) VALUES (16, 3)

INSERT INTO detalle_prestamo_material (ejemplar_material_id, prestamo_material_id) VALUES (1, 1)
INSERT INTO detalle_prestamo_material (ejemplar_material_id, prestamo_material_id) VALUES (2, 1)
INSERT INTO detalle_prestamo_material (ejemplar_material_id, prestamo_material_id) VALUES (3, 1)
INSERT INTO detalle_prestamo_material (ejemplar_material_id, prestamo_material_id) VALUES (7, 2)
INSERT INTO detalle_prestamo_material (ejemplar_material_id, prestamo_material_id) VALUES (8, 2)
INSERT INTO detalle_prestamo_material (ejemplar_material_id, prestamo_material_id) VALUES (17, 3)
INSERT INTO detalle_prestamo_material (ejemplar_material_id, prestamo_material_id) VALUES (18, 3)

--pass = hashira2002
INSERT INTO usuario (username, password, email, nombres, apellidos, habilitado) VALUES ('aloyolaa', '$2a$10$u0cWE3L8YzmhMgekij7MZu4ZDHM2p.L/uwgk4eBnA9QqKqRrAYkme', 'aloyolaa@gmail.com', 'Alan', 'Loyola', true)
--pass = 12345678
INSERT INTO usuario (username, password, email, nombres, apellidos, habilitado) VALUES ('gabriel', '$2a$10$pJPnIpcoIKp3Ur2i6OO2redUcV.MCJ8h828xyD4PNtyVFyLlRCdlC', 'gabriel@gmail.com', 'Gabriel', 'Loyola', true)

INSERT INTO rol (nombre) VALUES ('ROLE_ADMIN')
INSERT INTO rol (nombre) VALUES ('ROLE_USER')

INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 1)
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (2, 2)