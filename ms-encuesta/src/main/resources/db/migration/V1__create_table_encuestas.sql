CREATE TABLE encuestas (
    id BIGSERIAL PRIMARY KEY,
    reparacion_id BIGINT NOT NULL UNIQUE,
    rut_cliente VARCHAR(20) NOT NULL,
    puntuacion INTEGER NOT NULL,
    comentario VARCHAR(500),
    fecha TIMESTAMP NOT NULL
);
