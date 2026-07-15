CREATE TABLE eventos_auditoria (
    id BIGSERIAL PRIMARY KEY,
    servicio_origen VARCHAR(80) NOT NULL,
    accion VARCHAR(120) NOT NULL,
    detalle VARCHAR(500),
    fecha TIMESTAMP NOT NULL
);
