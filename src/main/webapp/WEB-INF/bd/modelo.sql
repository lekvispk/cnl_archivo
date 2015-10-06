drop database if exists gestiondoc;

create database if not exists gestiondoc;

use gestiondoc;

-- ------------------------------------------------------------
-- Tabla maestra con los tipos de actos  registrados y utilizados para los filtros
-- ------------------------------------------------------------

CREATE TABLE tipo_acto (
  id_acto INTEGER NOT NULL AUTO_INCREMENT,
  nombre_acto VARCHAR(100) NULL,
  estado INTEGER NULL,
  fecha_creacion DATETIME NULL,
  PRIMARY KEY(id_acto)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla para registrar la lista de todos los permisos disponibles en el sistema
-- ------------------------------------------------------------

CREATE TABLE sec_permisos (
  id_permiso INTEGER NOT NULL AUTO_INCREMENT,
  desc_permiso VARCHAR(50) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(id_permiso)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla Maestra con lista de tipos de solicittudes que se pueden realizar
-- Testimonio, 
-- parte notarial, 
-- copia simple
-- conclusion de escritura
-- ------------------------------------------------------------

CREATE TABLE tipo_solicitud (
  id_tipo_solicitud INTEGER NOT NULL AUTO_INCREMENT,
  nombre_tipo_solicitud VARCHAR(50) NULL,
  costo_servicio DECIMAL NULL,
  estado INTEGER NULL,
  fecha_creacion DATETIME NULL,
  PRIMARY KEY(id_tipo_solicitud)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tipos de relaciones: 
-- Comprador
-- Vendedor
-- etc...
-- ------------------------------------------------------------

CREATE TABLE tipo_relacion (
  idTipoRelacion INTEGER NOT NULL AUTO_INCREMENT,
  relDescripcion VARCHAR(150) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idTipoRelacion)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- lista con los tipos de documentos que se pueden usar:
-- DNI 
-- Pasaporte
-- Carnet de Extranjeria (CE)
-- ------------------------------------------------------------

CREATE TABLE documento_identidad (
  id_documento INTEGER NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(150) NULL,
  abreviacion VARCHAR(25) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(id_documento)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Listado de notarias
-- ------------------------------------------------------------

CREATE TABLE notaria (
  id_notaria INTEGER NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(250) NULL,
  estado INTEGER NULL,
  fec_creacion DATETIME NULL,
  PRIMARY KEY(id_notaria)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla para registrar informacion completa de la persona. El ID se pasa como FK para la tabla sec_usuarios
-- ------------------------------------------------------------

CREATE TABLE persona (
  id_persona INTEGER NOT NULL AUTO_INCREMENT,
  id_documento INTEGER NOT NULL,
  nombre VARCHAR(100) NULL,
  ape_paterno VARCHAR(50) NULL,
  ape_materno VARCHAR(50) NULL,
  cargo VARCHAR(100) NULL,
  grado VARCHAR(100) NULL,
  nombre_completo VARCHAR(200) NULL,
  num_documento VARCHAR(20) NULL,
  fecha_creacion DATETIME NULL,
  fecha_modificacion DATETIME NULL,
  usuario_creacion VARCHAR(50) NULL,
  usuario_modificacion VARCHAR(50) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(id_persona),
  FOREIGN KEY(id_documento)
    REFERENCES documento_identidad(id_documento)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla para registrar informacion completa de usuarios. Los datos de la persona son obtenidos desde la tabla persona.
-- ------------------------------------------------------------

CREATE TABLE sec_usuarios (
  username VARCHAR(50) NOT NULL,
  clave VARCHAR(50) NULL,
  estado INT NULL,
  fecha_creacion DATETIME NULL,
  id_persona INTEGER NULL,
  PRIMARY KEY(username),
  FOREIGN KEY(id_persona)
    REFERENCES persona(id_persona)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- datos de cada una de las escrituras  en la base de datos
-- ------------------------------------------------------------

CREATE TABLE escritura (
  id_escritura INTEGER NOT NULL AUTO_INCREMENT,
  id_notaria INTEGER NOT NULL,
  id_acto INTEGER NOT NULL,
  kardex VARCHAR(10) NULL,
  tram_fecha_registro DATETIME NULL,
  numero_folios VARCHAR(10) NULL,
  numero_instrumento VARCHAR(10) NULL,
  numero_minuta VARCHAR(5) NULL,
  numero_doc VARCHAR(100) NULL,
  cantidad_hojas INTEGER NULL,
  tipo_fojas INTEGER NULL,
  estado_escritura INTEGER NULL,
  firmas_restantes INTEGER NULL,
  estado INTEGER NULL,
  fecha_creacion DATETIME NULL,
  fecha_modificacion DATETIME NULL,
  usuario_creacion VARCHAR(50) NULL,
  usuario_modificacion VARCHAR(40) NULL,
  PRIMARY KEY(id_escritura),
  FOREIGN KEY(id_acto)
    REFERENCES tipo_acto(id_acto)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_notaria)
    REFERENCES notaria(id_notaria)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Se registra cada una de las personas y el tipo de participacion que tuvo en la escritura
-- ------------------------------------------------------------

CREATE TABLE persona_escritura (
  id_escritura INTEGER NOT NULL,
  id_persona INTEGER NOT NULL,
  idTipoRelacion INTEGER NOT NULL,
  estado INTEGER NULL,
  PRIMARY KEY(id_escritura, id_persona),
  FOREIGN KEY(id_escritura)
    REFERENCES escritura(id_escritura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_persona)
    REFERENCES persona(id_persona)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idTipoRelacion)
    REFERENCES tipo_relacion(idTipoRelacion)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- tabla para registrar informacion de las solicitudes que se realizan al CNL
-- ------------------------------------------------------------

CREATE TABLE solicitud (
  idsolicitud INTEGER NOT NULL AUTO_INCREMENT,
  id_notaria INTEGER NOT NULL,
  id_persona INTEGER NOT NULL,
  id_tipo_solicitud INTEGER NOT NULL,
  id_acto INTEGER NOT NULL,
  cod_solicitud VARCHAR(7) NULL,
  fecha_ingreso DATETIME NULL,
  fecha_solucion DATETIME NULL,
  tram_comprador VARCHAR(250) NULL,
  tram_vendedor VARCHAR(250) NULL,
  tram_escritura VARCHAR(20) NULL,
  tram_fecha_escritura DATETIME NULL,
  tram_kardex VARCHAR(20) NULL,
  tram_folios VARCHAR(10) NULL,
  tram_fojas VARCHAR(5) NULL,
  tram_minuta_num VARCHAR(10) NULL,
  tram_instrumento_num VARCHAR(10) NULL,
  tram_solicitado VARCHAR(250) NULL,
  tram_apod_telefono VARCHAR(20) NULL,
  tram_apoderado VARCHAR(250) NULL,
  tram_apod_documento VARCHAR(12) NULL,
  tram_apod_direccion VARCHAR(250) NULL,
  tipo_comprobante INTEGER NULL,
  numero_ruc VARCHAR(12) NULL,
  estado INTEGER NULL,
  fecha_creacion DATETIME NULL,
  fecha_modificacion DATETIME NULL,
  usuario_creacion VARCHAR(50) NULL,
  usuario_modificacion VARCHAR(50) NULL,
  PRIMARY KEY(idsolicitud),
  FOREIGN KEY(id_tipo_solicitud)
    REFERENCES tipo_solicitud(id_tipo_solicitud)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_acto)
    REFERENCES tipo_acto(id_acto)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_persona)
    REFERENCES persona(id_persona)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_notaria)
    REFERENCES notaria(id_notaria)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Una vez que se obtiene la escritura deseada en la solicitud, el cliente decide si es que realizará o no el tramite correspondiente. De continuar con el proceso se inicia el tramite y se le indica el costo del servicio
-- ------------------------------------------------------------

CREATE TABLE tramite (
  id_tramite INTEGER NOT NULL AUTO_INCREMENT,
  idsolicitud INTEGER NOT NULL,
  cant_hojas INTEGER NULL,
  costo_hoja DECIMAL NULL,
  costo_total DECIMAL NULL,
  informe_solicitud TEXT NULL,
  observaciones_notario TEXT NULL,
  fecha_conclusion DATETIME NULL,
  detalle_notificacion TEXT NULL,
  fecha_creacion DATETIME NULL,
  estado INTEGER NULL,
  PRIMARY KEY(id_tramite),
  FOREIGN KEY(idsolicitud)
    REFERENCES solicitud(idsolicitud)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla en donde se almacenan los archivos digitales
-- ------------------------------------------------------------

CREATE TABLE archivo (
  id_archivo INTEGER NOT NULL AUTO_INCREMENT,
  id_escritura INTEGER NOT NULL,
  nombre VARCHAR(100) NULL,
  archivo LONGBLOB NULL,
  PRIMARY KEY(id_archivo),
  FOREIGN KEY(id_escritura)
    REFERENCES escritura(id_escritura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE sec_authorities (
  username VARCHAR(50) NOT NULL,
  authorithy VARCHAR(50) NOT NULL,
  PRIMARY KEY(username, authorithy),
  FOREIGN KEY(username)
    REFERENCES sec_usuarios(username)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE solicitud_tramite (
  idsolicitud INTEGER NOT NULL,
  id_escritura INTEGER NOT NULL,
  fec_solicitud DATETIME NULL,
  archivo LONGBLOB NULL,
  estado INT NULL,
  PRIMARY KEY(idsolicitud, id_escritura),
  FOREIGN KEY(idsolicitud)
    REFERENCES solicitud(idsolicitud)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_escritura)
    REFERENCES escritura(id_escritura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla que guarda cada uno de los archivos adjuntos que son requisitos para finalizar el tramite
-- ------------------------------------------------------------

CREATE TABLE tramite_adjuntos (
  id_adjunto INTEGER NOT NULL AUTO_INCREMENT,
  id_tramite INTEGER NOT NULL,
  nombre VARCHAR(250) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(id_adjunto),
  FOREIGN KEY(id_tramite)
    REFERENCES tramite(id_tramite)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;


SET NAMES latin1;

SET SQL_MODE='';

insert into `gestiondoc`.`tipo_acto` (id_acto, nombre_acto, estado) values
(1, 'Compra Venta de Inmueble', 1),
(2, 'Compra Venta de Vehículo', 1),
(3, 'Sucesion Intestada', 1),
(4, 'Testimonio', 1),
(5, 'Poderes', 1),
(6, 'Rectificación de Partida', 1),
(7, 'Testamento', 1);

insert into `gestiondoc`.`tipo_solicitud` (id_tipo_solicitud, nombre_tipo_solicitud,estado,fecha_creacion) values
(1, 'Testimonio',1,now()),
(2, 'Pate Notarial',1,now()),
(3, 'Copia Simple',1,now());

insert into `gestiondoc`.`tipo_relacion` (idTipoRelacion, relDescripcion, estado) values
(1, 'Comprador', 1),
(2, 'Vendedor', 1),
(3, 'Apoderado', 1),
(4, 'Implicado', 1),
(5, 'Solicitante', 1),
(6, 'Informante',1),
(7, 'Opositor',1);

insert into `gestiondoc`.`sec_permisos` (id_permiso, desc_permiso, estado) values
(1, 'ROLE_ADMIN', 1),
(2, 'ROLE_CONSULTA', 1),
(3, 'ROLE_TRAMITE', 1),
(4, 'ROLE_ARCHIVO', 1),
(5, 'ROLE_MP', 1),
(6, 'ROLE_NOTARIO', 1),
(7, 'ROLE_USER', 1);

insert into `gestiondoc`.`notaria` (id_notaria, nombre, estado, fec_creacion) values
(1, 'Notaria Almeyda', 1, now()),
(2, 'Notaria Aliaga', 1, now()),
(3, 'Notaria Alberti', 1, now()),
(4, 'Notaria Bazan Naveda', 1, now()),
(5, 'Notaria Delgado', 1, now()),
(6, 'Notaria Herrera Portuondo', 1, now()),
(7, 'Notaria Jara Briceño', 1, now()),
(8, 'Notaria Paino', 1, now());

insert into `gestiondoc`.`documento_identidad` (id_documento, nombre, abreviacion, estado) values 
(1, 'Documento Nacional de Identidad', 'D.N.I.', 1),
(2, 'Pasaporte', 'Pasaporte', 1);

insert into `gestiondoc`.`persona` (id_persona, nombre, ape_paterno, ape_materno, cargo, grado, nombre_completo, num_documento, id_documento, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, estado) values
(1, 'Wilbert Pedro', 'Manrique', 'Quispe', '', '', 'Wilbert Pedro Manrique Quispe', '00000000', 1, now(), null, 'ecampos', null, 1),
(2, 'Elvis Ruben', 'Campos', 'Mori', '', '', 'Elvis Ruben Campos Mori', '44194490', 1, now(), null, 'ecampos', null, 1),
(3, 'Juan', 'Culqui', 'P.', '', '', 'Juan Culqui P.', '44191000', 1, now(), null, 'ecampos', null, 1),
(4, 'Jose Gabriel', 'Gomero', 'Valdez', '', '', 'Jose Gabriel Gomero Valdez', '44191001', 1, now(), null, 'ecampos', null, 1),
(5, 'Juan Carlos', 'Vargas', 'Ponce', '', '', 'Juan Carlos Vargas Ponce', '44191002', 1, now(), null, 'ecampos', null, 1),
(6, 'Gisela Patricia', 'Jara', 'Briceño', '', '', 'Gisela Patricia Jara Briceño', '07961488', 1, now(), null, 'ecampos', null, 1),
(7, 'Juan', 'Perez', '', '', '', 'Juan Perez', '11111111', 1, now(), null, 'ecampos', null, 1);

insert into `gestiondoc`.`sec_usuarios` (username, clave, estado, id_persona) values
('wmanrique', 'e10adc3949ba59abbe56e057f20f883e', 1,  1),
('ecampos', 'e10adc3949ba59abbe56e057f20f883e', 1, 2),
('jculqui', 'e10adc3949ba59abbe56e057f20f883e', 1, 3),
('jgomero', 'e10adc3949ba59abbe56e057f20f883e', 1, 4),
('jvargas', 'e10adc3949ba59abbe56e057f20f883e', 1, 5),
('gjara', 'e10adc3949ba59abbe56e057f20f883e', 1, 6);

insert into `gestiondoc`.`sec_authorities` (username, authorithy) values
('ecampos', 'ROLE_ADMIN'),('ecampos', 'ROLE_USER'),
('wmanrique', 'ROLE_CONSULTA'),('wmanrique', 'ROLE_USER'),
('jculqui', 'ROLE_ARCHIVO'),('jculqui', 'ROLE_USER'),
('jgomero', 'ROLE_ARCHIVO'),('jgomero', 'ROLE_USER'),
('jvargas', 'ROLE_MP'),('jvargas', 'ROLE_USER'),
('gjara', 'ROLE_NOTARIO'),('gjara', 'ROLE_USER');

insert into `gestiondoc`.`solicitud` 
	(idsolicitud, id_notaria, id_persona, id_tipo_solicitud, 
	id_acto, cod_solicitud, fecha_ingreso, fecha_solucion, 
	tram_comprador, tram_vendedor, tram_escritura, tram_fecha_escritura, 
	tram_kardex, tram_fojas, tram_minuta_num, tram_instrumento_num, 
	tram_solicitado, tram_apod_telefono, tram_apoderado, tram_apod_documento, 
	tram_apod_direccion, tipo_comprobante, numero_ruc, estado, 
	fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion
	)
	values
	(1, 1, 7, 3, 
	1, '0000001', now(), null, 
	'Carlos Perez', 'Santiago Segura', '00002500', '2010-07-05', 
	null, null, null, null, 
	'tram_solicitado', '5583456', '', '', 
	'', 1, null, 1, 
	now(), null, 'ecampos', null
	);