-- =====================================================================
-- SISTEMA DE CONTROL DE MENSAJERIA - Script Oracle (12c o superior)
-- Basado en el Modelo Relacional (Fase 2)
-- Ejecutar conectado con el usuario/esquema que usara la aplicacion.
-- =====================================================================

-- Limpieza (opcional, para volver a crear todo)
-- DROP TABLE ENVIO CASCADE CONSTRAINTS;
-- DROP TABLE PAQUETE CASCADE CONSTRAINTS;
-- DROP TABLE MENSAJERO CASCADE CONSTRAINTS;
-- DROP TABLE CLIENTE CASCADE CONSTRAINTS;
-- DROP TABLE USUARIO CASCADE CONSTRAINTS;
-- DROP TABLE ROL CASCADE CONSTRAINTS;

CREATE TABLE ROL (
    id_rol       NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_rol   VARCHAR2(40)  NOT NULL UNIQUE,
    descripcion  VARCHAR2(200)
);

CREATE TABLE USUARIO (
    id_usuario      NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_rol          NUMBER        NOT NULL,
    nombre_usuario  VARCHAR2(50)  NOT NULL UNIQUE,
    clave_hash      VARCHAR2(255) NOT NULL,
    estado          VARCHAR2(15)  DEFAULT 'Activo' NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES ROL(id_rol),
    CONSTRAINT ck_usuario_estado CHECK (estado IN ('Activo','Inactivo'))
);

CREATE TABLE CLIENTE (
    id_cliente  NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre      VARCHAR2(100) NOT NULL,
    telefono    VARCHAR2(20),
    direccion   VARCHAR2(200),
    correo      VARCHAR2(100)
);

CREATE TABLE MENSAJERO (
    id_mensajero  NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre        VARCHAR2(100) NOT NULL,
    telefono      VARCHAR2(20),
    direccion     VARCHAR2(200),
    estado        VARCHAR2(15) DEFAULT 'Activo' NOT NULL,
    CONSTRAINT ck_mensajero_estado CHECK (estado IN ('Activo','Inactivo'))
);

CREATE TABLE PAQUETE (
    id_paquete   NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tipo         VARCHAR2(30)  NOT NULL,
    descripcion  VARCHAR2(200) NOT NULL,
    peso         NUMBER(8,2),
    dimensiones  VARCHAR2(100),
    CONSTRAINT ck_paquete_tipo CHECK (tipo IN ('Paquete','Documento'))
);

-- NOTA: Se agregan 4 columnas al modelo de la Fase 2 porque la Fase 1
-- indica registrar los datos del destinatario, la fecha de entrega y
-- quien recibio el paquete (Pasos 4 y 7 del flujo).
CREATE TABLE ENVIO (
    id_envio              NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_cliente            NUMBER        NOT NULL,
    id_paquete            NUMBER        NOT NULL UNIQUE,   -- relacion 1:1 con PAQUETE
    id_mensajero          NUMBER,                          -- opcional (0..1)
    fecha_envio           DATE          DEFAULT SYSDATE NOT NULL,
    direccion_destino     VARCHAR2(200) NOT NULL,
    estado                VARCHAR2(20)  DEFAULT 'Registrado' NOT NULL,
    observaciones         VARCHAR2(300),
    nombre_destinatario   VARCHAR2(100) NOT NULL,          -- adicional (Fase 1)
    telefono_destinatario VARCHAR2(20),                    -- adicional (Fase 1)
    fecha_entrega         DATE,                            -- adicional (Fase 1)
    recibido_por          VARCHAR2(100),                   -- adicional (Fase 1)
    CONSTRAINT fk_envio_cliente   FOREIGN KEY (id_cliente)   REFERENCES CLIENTE(id_cliente),
    CONSTRAINT fk_envio_paquete   FOREIGN KEY (id_paquete)   REFERENCES PAQUETE(id_paquete),
    CONSTRAINT fk_envio_mensajero FOREIGN KEY (id_mensajero) REFERENCES MENSAJERO(id_mensajero),
    CONSTRAINT ck_envio_estado CHECK (estado IN
        ('Registrado','Asignado','En tránsito','Entregado','Entrega fallida','Cancelado'))
);

CREATE INDEX ix_envio_cliente   ON ENVIO(id_cliente);
CREATE INDEX ix_envio_mensajero ON ENVIO(id_mensajero);
CREATE INDEX ix_envio_estado    ON ENVIO(estado);

-- Datos iniciales: roles
INSERT INTO ROL (nombre_rol, descripcion) VALUES ('Administrador', 'Supervisa el sistema, administra usuarios, clientes, mensajeros y reportes');
INSERT INTO ROL (nombre_rol, descripcion) VALUES ('Operador', 'Registra clientes, paquetes y envios; asigna mensajeros y actualiza estados');
COMMIT;

-- El usuario administrador inicial (admin / admin123) se crea
-- automaticamente la primera vez que se abre la aplicacion,
-- guardando la clave como hash seguro (PBKDF2). Cambiela al ingresar.
