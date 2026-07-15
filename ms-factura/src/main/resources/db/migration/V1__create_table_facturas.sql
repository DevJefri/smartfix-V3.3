CREATE TABLE facturas (
    id BIGSERIAL PRIMARY KEY,
    folio VARCHAR(20) NOT NULL UNIQUE,
    reparacion_id BIGINT NOT NULL,
    rut_cliente VARCHAR(20) NOT NULL,
    monto NUMERIC(12, 2) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_emision TIMESTAMP NOT NULL
);
