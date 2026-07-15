package cl.ms_encuesta.exception;

public class EncuestaDuplicadaException extends RuntimeException {
    public EncuestaDuplicadaException(Long reparacionId) {
        super("Ya existe una encuesta registrada para la reparacion con id: " + reparacionId);
    }
}
