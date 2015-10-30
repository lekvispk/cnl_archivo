drop database if exists gestiondoc;

create database if not exists gestiondoc 
DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;

use gestiondoc;

SET NAMES latin1;

SET SQL_MODE='';

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
-- Listado de notarias
-- ------------------------------------------------------------

CREATE TABLE notaria (
  id_notaria INTEGER NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(250) NULL,
  estado INTEGER NULL,
  email VARCHAR(250) NULL,
  fec_creacion DATETIME NULL,
  PRIMARY KEY(id_notaria)
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
-- datos de cada una de las escrituras  en la base de datos
-- ------------------------------------------------------------

CREATE TABLE escritura (
  id_escritura INTEGER NOT NULL AUTO_INCREMENT,
  id_notaria INTEGER NOT NULL,
  kardex VARCHAR(10) NULL,
  fecha_escritura DATETIME NULL,
  numero_instrumento VARCHAR(10) NULL,
  numero_minuta VARCHAR(5) NULL,
  numero_doc VARCHAR(100) NULL,
  numero_factura VARCHAR(11) NULL,
  numero_folios VARCHAR(10) NULL,
  cantidad_fojas INTEGER NULL,
  tipo_fojas INTEGER NULL,
  numero_fojas INTEGER NULL,
  estado_escritura INTEGER NULL,
  firmas_restantes INTEGER NULL,
  anio_tomo INTEGER NULL,
  numero_tomo INTEGER NULL,
  ubicacion_fisica VARCHAR(2500) NULL,
  ubicacion_digital VARCHAR(2500) NULL,
  estado INTEGER NULL,
  fecha_creacion DATETIME NULL,
  fecha_modificacion DATETIME NULL,
  usuario_creacion VARCHAR(50) NULL,
  usuario_modificacion VARCHAR(40) NULL,
  PRIMARY KEY(id_escritura),
  FOREIGN KEY(id_notaria)
    REFERENCES notaria(id_notaria)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
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
  email VARCHAR(250) NULL,
  telefono VARCHAR(12) NULL,
  estado INTEGER NULL,
  fecha_creacion DATETIME NULL,
  fecha_modificacion DATETIME NULL,
  usuario_creacion VARCHAR(50) NULL,
  usuario_modificacion VARCHAR(50) NULL,
  PRIMARY KEY(id_persona),
  FOREIGN KEY(id_documento)
    REFERENCES documento_identidad(id_documento)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- tabla en donde se giuadan cada uno de los actos que estan registrados en la escritura publica
-- ------------------------------------------------------------

CREATE TABLE actos_escritura (
  id_acto INTEGER NOT NULL,
  id_escritura INTEGER NOT NULL,
  estado INTEGER NULL,
  PRIMARY KEY(id_acto, id_escritura),
  FOREIGN KEY(id_acto)
    REFERENCES tipo_acto(id_acto)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_escritura)
    REFERENCES escritura(id_escritura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla de Otorgantes. 
-- Se registra cada una de las personas y el tipo de participacion que tuvo en la escritura.
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
  id_notaria INTEGER NULL,
  id_persona INTEGER NOT NULL,
  id_tipo_solicitud INTEGER NOT NULL,
  id_acto INTEGER NULL,
  cod_solicitud VARCHAR(7) NULL,
  fecha_ingreso DATETIME NULL,
  fecha_solucion DATETIME NULL,
  tram_comprador VARCHAR(250) NULL,
  tram_comprador_paterno VARCHAR(150) NULL,
  tram_comprador_materno VARCHAR(150) NULL,
  tram_vendedor VARCHAR(250) NULL,
  tram_vendedor_paterno VARCHAR(150) NULL,
  tram_vendedor_materno VARCHAR(150) NULL,
  tram_escritura VARCHAR(20) NULL,
  tram_fecha_inicial DATETIME NULL,
  tram_fecha_final DATETIME NULL,
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
  razon_social VARCHAR(150) NULL,
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

-- ------------------------------------------------------------
-- Una vez que se obtiene la escritura deseada en la solicitud, el cliente decide si es que realizar� o no el tramite correspondiente. De continuar con el proceso se inicia el tramite y se le indica el costo del servicio. 
-- Cuando se deriva una solicitud, se hace a partir de una escritura del resultado de la busqueda.
-- ------------------------------------------------------------

CREATE TABLE tramite (
  id_tramite INTEGER NOT NULL AUTO_INCREMENT,
  id_escritura INTEGER NOT NULL,
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
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_escritura)
    REFERENCES escritura(id_escritura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- TABLA A ELIMINAR, NO TIENE USO EN ESTA NUEVA VERSION, NO SE SI EN LA ANTIGUA SERVIA PARA ALGO
-- ------------------------------------------------------------

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
-- registro de los movimientos que se van realizando sobre un tramite.
-- Cuando a un usuario deriva un tramite a otro usuario se debe crear un nuevo registro y actualizar el estado de todos los anteriores para que esten inactivos. 
-- El usuario Tramite tendra los nuevos tramites en la bandeja Por Derivar.
-- existe un registro en donde usuario receptor es el de tramite. Emisor posiblemente el mismo o sino alguien de MP. tramite.estado actualizado
-- Si envia el tramite a un notario ahora el tramite estara en la bandeja Derivados
-- Se crea un registro donde el emisor es el de tramite y el eceptor un notario. los antiguos registros estan con estado 0. tramite.estado acutlaizado.
-- El Notario ver� sus tr�mites en Derivados. Cuado lo conteste pasar� a la bandeja de Respondidos. 
-- Al contestar el tramite se crea un registro donde emisor es el Notario y receptor el usuario de Tramite. Tramite.estado actualizado
-- El uario de tramite ahora tendra tramites en la bandeja Respondidos y cuando llegue la fecha de concluirlos, el mismo llo actualizara.
-- Al concluirlo se crea un registro donde emisor y receptor es el mismo usuario de Tramite. Tramite.estado actualizado
-- ------------------------------------------------------------

CREATE TABLE tramite_usuario (
  id_registro INTEGER NOT NULL AUTO_INCREMENT,
  username_receptor VARCHAR(50) NOT NULL,
  username_emisor VARCHAR(50) NOT NULL,
  id_tramite INTEGER NOT NULL,
  fecha_registro DATETIME NULL,
  estado INTEGER NULL,
  PRIMARY KEY(id_registro),
  FOREIGN KEY(username_emisor)
    REFERENCES sec_usuarios(username)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_tramite)
    REFERENCES tramite(id_tramite)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(username_receptor)
    REFERENCES sec_usuarios(username)
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




insert into `gestiondoc`.`tipo_acto` (id_acto, nombre_acto, estado) values
(1, 'Compra Venta de Inmueble', 1),
(2, 'Compra Venta de Veh�culo', 1),
(3, 'Sucesion Intestada', 1),
(4, 'Testimonio', 1),
(5, 'Poderes', 1),
(6, 'Rectificaci�n de Partida', 1),
(7, 'Testamento', 1);

insert into `gestiondoc`.`tipo_solicitud` (id_tipo_solicitud, nombre_tipo_solicitud,costo_servicio,estado,fecha_creacion) values
(1, 'Testimonio',50,1,now()),
(2, 'Pate Notarial',5,1,now()),
(3, 'Copia Simple',5,1,now()),
(4, 'Conclusion',100,1,now());

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

insert into `gestiondoc`.`notaria` (id_notaria, nombre, estado, email,fec_creacion) values
(1, 'Notaria Almeyda', 1, 'notaria@notariaalmeyda.com',now()),
(2, 'Notaria Aliaga', 1, 'notaria@notariaaliaga.com',now()),
(3, 'Notaria Alberti', 1, 'notaria@notariaalberti.com', now()),
(4, 'Notaria Bazan Naveda', 1,  'notaria@notariabazannaveda.com',now()),
(5, 'Notaria Delgado', 1,  'notaria@notariadelgado.com',now()),
(6, 'Notaria Herrera Portuondo', 1,  'notaria@notariaherrera.com',now()),
(7, 'Notaria Jara Brice�o', 1,  'notaria@notariajara.com',now()),
(8, 'Notaria Paino', 1,  'notaria@notariapaino.com',now()),
(9, 'Notaria Sotomayor Bernos', 1,  'notaria@notariasotomayorbernos.com',now()),
(10, 'Notaria Sotomayor Vitela', 1,  'notaria@notariatotomayorvitela.com',now()),
(11, 'Notaria Canelo', 1,'notaria@notariacanelo.com',now());

insert into `gestiondoc`.`documento_identidad` (id_documento, nombre, abreviacion, estado) values 
(1, 'Documento Nacional de Identidad', 'D.N.I.', 1),
(2, 'Pasaporte', 'Pasaporte', 1),
(3, 'Carnet de Extranjer�a', 'Pasaporte', 1);

insert into `gestiondoc`.`persona` (id_persona, nombre, ape_paterno, ape_materno, cargo, grado, nombre_completo, num_documento, id_documento, fecha_creacion, fecha_modificacion, usuario_creacion, usuario_modificacion, estado,email) values
(1, 'Wilbert Pedro', 'Manrique', 'Quispe', '', '', 'Wilbert Pedro Manrique Quispe', '00000000', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(2, 'Elvis Ruben', 'Campos', 'Mori', '', '', 'Elvis Ruben Campos Mori', '44194490', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(3, 'Juan', 'Culqui', 'P.', '', '', 'Juan Culqui P.', '44191000', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(4, 'Jose Gabriel', 'Gomero', 'Valdez', '', '', 'Jose Gabriel Gomero Valdez', '44191001', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(5, 'Juan Carlos', 'Vargas', 'Ponce', '', '', 'Juan Carlos Vargas Ponce', '44191002', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(6, 'Gisela Patricia', 'Jara', 'Brice�o', '', '', 'Gisela Patricia Jara Brice�o', '07961488', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(7, 'Juana Alicia', 'Perez', 'De Chavarria', '', '', 'Juana Alicia Perez de Chavarria', '02113318', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(8, 'Carlos', 'Tarazona', 'Perez', '', '', 'Carlos Tarazona Perez', '44111263', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(9, 'Luis Angel', 'Ulloa', 'Pe�a', '', '', 'Luis Angel Ulloa Pe�a', '33132456', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(10, 'Enrique', 'Cotrina', 'Tirado', '', '', 'Enrique Cotrina Tirado', '22111460', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(11, 'Tadeo', 'Albornoz', 'Pascual', '', '', 'Tadeo Albornoz Campos', '03111284', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(12, 'Bianka', 'Vazques', 'Portocarrero', '', '', 'Bianka Vazques Portocarrero', '44111893', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(13, 'Lucero', 'Santiesteban', 'Carrasco', '', '', 'Lucero Santiesteban Carrasco', '44111820', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(14, 'Alberto', 'Gamonal', 'Cabezas', '', '', 'Alberto Gamonal Cabezas', '44111821', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(15, 'Aurelia', 'Salazar', 'Escobar', '', '', 'Aurelia Salazar Escobar', '44111822', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(16, 'Karen', 'Shaw', 'Acu�a', '', '', 'Karen Shaw Acu�a', '44111823', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(17, 'Lucila', 'Tarazona', 'Alvarado', '', '', 'Lucila Tarazona Alvarado', '44111824', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(18, 'Carmen', 'Bernal', 'Prudencio', '', '', 'Carmen Bernal Prudencio', '44111825', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(19, 'Ernesto', 'Portocarrero', 'Guerra', '', '', 'Ernesto Portocarrero Guerra', '44111826', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(20, 'Ericka', 'P�rez', 'Huarcaya', '', '', 'Ericka P�rez Huarcaya', '44111827', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(21, 'Karina', 'Calderon', 'Colchon', '', '', 'Karina Calderon Colchon', '44111828', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(22, 'Robert', 'Santana', 'Espejo', '', '', 'Robert Santana Espejo', '44111829', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(23, 'Robin', 'Santa Cruz', 'Cama', '', '', 'Robin Santa Cruz Cama', '44111830', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(24, 'Carlos', 'Mu�oz', 'Vera', '', '', 'Carlos Mu�oz Vera', '44111831', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(25, 'Luis', 'Campos', 'Agurto', '', '', 'Luis Campos Agusto', '44111832', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(26, 'Henry', 'Salgado', 'Flores', '', '', 'Henry Salgado Flores', '44111833', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(27, 'Saul', 'Sotomayor', 'Ozco', '', '', 'Saul Sotomayor Flores', '44111834', 1, now(), null, 'ecampos', null, 1,'a@a.com'),
(28, 'Gerardo', 'Berrocal', 'Salinas', '', '', 'Gerardo Berrocal Salinas', '45176535', 1, now(), null, 'ecampos', null, 1,'a@a.com');

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


insert into `gestiondoc`.`escritura` (id_escritura, id_notaria, kardex, fecha_escritura, numero_instrumento, numero_minuta, numero_doc, numero_folios, cantidad_fojas, tipo_fojas, numero_fojas, estado_escritura, firmas_restantes, anio_tomo, numero_tomo, ubicacion_fisica, ubicacion_digital, estado, fecha_creacion, fecha_modificacion, usuario_creacion,  usuario_modificacion) 	values
(1, 1, '002300', '2010-01-0', '002300', '002300', '002300', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante uno', 'disco externo uno', 1, now(), null, 'ecampos', null),
(2, 2, '011100', '2010-01-0', '011100', '011100', '011100', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante dos', 'disco externo uno', 1, now(), null, 'ecampos', null),
(3, 3, '099900', '2010-01-0', '099900', '099900', '099900', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante grande', 'disco externo uno', 1, now(), null, 'ecampos', null),
(4, 4, '044400', '2010-01-0', '044400', '044400', '044400', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante mediano', 'disco externo uno', 1, now(), null, 'ecampos', null),
(5, 5, '022200', '2010-01-0', '022200', '022200', '022200', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante final', 'disco externo uno', 1, now(), null, 'ecampos', null),
(6, 6, '033300', '2010-01-0', '033300', '077700', '033300', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante ultimo', 'disco externo uno', 1, now(), null, 'ecampos', null),
(7, 7, '066600', '2010-01-0', '066600', '066600', '066600', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante antiguo', 'disco externo uno', 1, now(), null, 'ecampos', null),
(8, 1, '077700', '2010-01-0', '077700', '077700', '077700', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante lateral', 'disco externo uno', 1, now(), null, 'ecampos', null),
(9, 2, '088800', '2010-01-0', '088800', '088800', '088800', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante marron', 'disco externo uno', 1, now(), null, 'ecampos', null),
(10,3, '055500', '2010-01-0', '055500', '055500', '077700', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante externo', 'disco externo uno', 1, now(), null, 'ecampos', null),
(11,4, '087400', '2010-01-0', '087400', '087400', '087400', 183, 3, 1, 3, 1, 0, 2010, 3, 'estante nuevo', 'disco externo uno', 1, now(), null, 'ecampos', null);

insert into `gestiondoc`.`actos_escritura` (id_acto, id_escritura) values
(1, 1),(2, 1),
(3, 2),(4, 2),
(4, 3),(5, 3),
(6, 4),(7, 4),
(1, 5),(3, 5),
(4, 6),(5, 6),
(6, 7),(2, 7);

insert into `gestiondoc`.`persona_escritura` (id_escritura, id_persona, idTipoRelacion, estado) values
(1, 10, 1, 1),(1, 11, 2, 1),
(2, 12, 1, 1),(2, 13, 2, 1),(2, 14, 3, 1),
(3, 15, 1, 1),(3, 16, 2, 1),
(4, 17, 1, 1),(4, 18, 2, 1),
(5, 19, 1, 1),(5, 20, 2, 1),
(6, 21, 1, 1),(6, 22, 2, 1),
(7, 23, 1, 1),(7, 24, 2, 1),
(8, 25, 1, 1),(8, 26, 2, 1),(8, 14, 3, 1),
(9, 27, 4, 1),(9, 28, 5, 1),(9, 10, 6, 1),(9, 12, 3, 1),
(10, 12, 1, 1),(10, 15, 2, 1),
(11, 16, 1, 1),(11, 17, 2, 1),(11, 19, 3, 1),(11, 25, 4, 1);

insert into `gestiondoc`.`solicitud` (idsolicitud, id_notaria, id_persona, id_tipo_solicitud, id_acto, cod_solicitud, 
fecha_ingreso, fecha_solucion, tram_comprador, tram_vendedor, tram_escritura, tram_fecha_inicial, tram_fecha_final,
 tram_kardex, tram_fojas, tram_minuta_num, tram_instrumento_num, tram_solicitado, tram_apod_telefono, tram_apoderado, 
tram_apod_documento, tram_apod_direccion, tipo_comprobante, numero_ruc, estado, fecha_creacion, fecha_modificacion, 
usuario_creacion, usuario_modificacion) values
(1, 1, 7, 1, 1, '0000001', now(), null, 'Carlos Perez', 'Santiago Segura', '077700', '2010-07-05','2010-08-05', '011100', null, null, null, 'tram_solicitado', '254 - 2458', '', '', '', 1, null, 1, now(), null, 'ecampos', null),
(2, null, 8, 3, 2, '0000002', now(), null, 'Victoria Almeyda', null, '00034560', '2010-07-05','2010-08-05', '011100', null, null, null, 'tram_solicitado', '254 - 2458', '', '', '', 1, null, 2, now(), null, 'ecampos', null),
(3, null, 9, 3, 3, '0000003', now(), null, 'Karen Risco', null, '00789500', '2010-07-05','2010-08-05', null, null, null, null, 'tram_solicitado', '254 - 2458', '', '', '', 1, null, 2, now(), null, 'ecampos', null),
(4, null, 10, 1, 4, '0000004', now(), null, 'Esteban Santiesteban', null, '00083245', '2010-07-05','2010-08-05', '099900', null, null, null, 'tram_solicitado', '254 - 2458', '', '', '', 3, null, 1, now(), null, 'ecampos', null),
(5, null, 11, 2, 5, '0000005', now(), null, 'Aurelia Vazques', null, null, '2010-07-05', '2010-08-05',null, null, null, null, 'tram_solicitado', '254 - 2458', '', '', '', 1, null, 2, now(), null, 'ecampos', null),
(6, 2, 12, 3, null, '0000006', now(), null, 'Orlando Sevillano', null, null, '2010-07-05', '2010-08-05',null, null, null, null, 'tram_solicitado', '254 - 2458', '', '', '', 1, null, 2, now(), null, 'ecampos', null),
(7, 3, 7, 3, null, '0000007', now(), null, 'Pa�l Solis', null, null, '2010-07-05', '2010-08-05',null, null, null, null, 'tram_solicitado', '254 - 2458', '', '', '', 1, null, 3, now(), null, 'ecampos', null),
(8, 4, 8, 3, null, '0000008', now(), null, 'Miguel Arturo Tuesta Sologorr�', null, '15902500', '2010-07-01', '2010-08-05','011100', null, null, null, 'tram_solicitado', '254 - 2458', '', '', '', 1, null, 1, now(), null, 'ecampos', null);

insert into `gestiondoc`.`tramite` (id_tramite, idsolicitud,id_escritura, cant_hojas, costo_hoja, costo_total, informe_solicitud, observaciones_notario, fecha_conclusion, detalle_notificacion, fecha_creacion, estado) values
(1, 1, 1, 5, 5, 25, 'Doctor, hay este tramite pendiente. favor de validar', 'Juan, coordinar para la conclusion', '2015-10-25', 'presetarse juntos a los otros paticipantes', now(), 6),
(2, 2, 3, 2, 2, 4, '', '', null, null, now(), 0),
(3, 3, 5, 1, 5, 5, 'Doctor, hay un tramite pendiente. Favor de validar', 'Juan, coordinar para la conclusion', '2015-10-25', '', now(), 5),
(4, 4, 7, 3, 5, 15, 'Doctor hay un nuevo tramite', 'Juan ,favor de coordinar', '2015-10-25', '', now(), 4),
(5, 5, 9, 6, 5, 30, 'Doctor, hay un nuevo tramite. favor de revisar. adjunto los documentos DNI de dos personas.', '', '2015-10-25', '', now(), 3),
(6, 6, 2, 1, 2, 2, null, null, null, null, now(), 2),
(7, 7, 4, 2, 2, 4, null, null, null, null, now(), 1),
(8, 8, 6, 6, 2, 12, null, null, null, null, now(), 1);

--  tramite.estado{ 0='ELIMINADO',1='REGISTRADO',2='DERIVADO',3='RESPONDIDO',4='NOTIFICADO',5='CONCLUIDO'}

insert into `gestiondoc`.`tramite_usuario` (id_registro, username_emisor,username_receptor, id_tramite, fecha_registro, estado) VALUES
(1,'jculqui','jculqui',1,now(),0),
(2,'jculqui','jculqui',2,now(),0),
(3,'jculqui','jculqui',3,now(),0),
(4,'jculqui','jculqui',4,now(),0),
(5,'jculqui','jculqui',5,now(),0),
(6,'jculqui','jculqui',6,now(),0),
(7,'jculqui','jculqui',7,now(),0),
(8,'jculqui','jculqui',8,now(),0),
(9,'jculqui','jculqui',1,now(),0),
(10,'jculqui','jculqui',2,now(),1),
(11,'jculqui','jculqui',3,now(),0),
(12,'jculqui','jculqui',4,now(),0),
(13,'jculqui','jculqui',5,now(),0),
(14,'jculqui','jculqui',6,now(),1),
(15,'jculqui','jculqui',7,now(),1),
(16,'jculqui','jculqui',8,now(),1),
(17,'jculqui','gjara',1,now(),0),
(18,'gjara','jculqui',1,now(),0),
(19,'jculqui','jculqui',1,now(),0),
(20,'jculqui','jculqui',1,now(),1),
(21,'jculqui','gjara',3,now(),0),
(22,'gjara','jculqui',3,now(),0),
(23,'jculqui','jculqui',3,now(),1),
(24,'jculqui','gjara',4,now(),0),
(25,'gjara','jculqui',4,now(),1),
(26,'jculqui','gjara',5,now(),1);
