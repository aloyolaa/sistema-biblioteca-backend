INSERT INTO autor (nombre) VALUES ('NO ESPECIFICA')
INSERT INTO autor (nombre) VALUES ('SANTILLANA')
INSERT INTO autor (nombre) VALUES ('MINISTERIO DE EDUCACIÓN')
INSERT INTO autor (nombre) VALUES ('JULIO VERNE')
INSERT INTO autor (nombre) VALUES ('WILSON URTECHO RODRIGUEZ')
INSERT INTO autor (nombre) VALUES ('SYLVIANO ROCHE')
INSERT INTO autor (nombre) VALUES ('MANUEL GONZALES PRADA')

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

INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('MATEMÁTICA I', 2018, 1, 20, 2, 2, 2, 2)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('MATEMÁTICA II', 2019, 2, 30, 2, 2, 2, 2)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('COMUNICACIÓN III', 2020, 3, 12, 3, 3, 3, 3)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('COMUNICACIÓN IV', 2020, 4, 2, 1, 2, 3, 3)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('PERSONAL SOCIAL V', 2022, 5, 1, 1, 3, 4, 4)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('CIENCIA Y AMBIENTE VI', 2021, 6, 12, 2, 4, 5, 5)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('LA VUELTA AL MUNDO EN OCHENTA DIAS ', 2000, 1, 13, 2, 2, 3, 3)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('GUIA DE APRENDIZAJE I', 2023, 1, 20, 3, 5, 1, 6)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('SESIONES DE APRENDIZAJE I', 2023, 1, 22, 4, 5, 1, 6)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('GUIA DE APRENDIZAJE II', 2023, 2, 32, 3, 5, 1, 6)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('SESIONES DE APRENDIZAJE II', 2022, 2, 12, 4, 5, 1, 6)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('ALGO TE IDENTIFICA', 2007, 4, 29, 2, 2, 3, 3)
INSERT INTO libro (titulo, anio, grado, cantidad, autor_id, categoria_id, editorial_id, area_id) VALUES ('CREO EN TI', 2005, 5, 24, 2, 2, 3, 3)

INSERT INTO material (nombre, lotes, unidades, area_id) VALUES ('JARRAS MEDIDORAS', 13, 3, 1)
INSERT INTO material (nombre, lotes, unidades, area_id) VALUES ('DADOS NUMÉRICOS', 1, 20, 1)
INSERT INTO material (nombre, lotes, unidades, area_id) VALUES ('CALCULADORAS', 9, 10, 1)
INSERT INTO material (nombre, lotes, unidades, area_id) VALUES ('FICHAS DE DIFERENTES TIPOS DE LETRAS', 1, 32, 2)
INSERT INTO material (nombre, lotes, unidades, area_id) VALUES ('IMANES', 7, 3, 4)
INSERT INTO material (nombre, lotes, unidades, area_id) VALUES ('LUPAS', 1, 6, 4)