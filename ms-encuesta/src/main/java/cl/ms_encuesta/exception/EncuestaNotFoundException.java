package cl.ms_encuesta.exception;

public class EncuestaNotFoundException extends RuntimeException {
    public EncuestaNotFoundException(Long reparacionId) {
        super("No se encontro una encuesta para la reparacion con id: " + reparacionId);
    }
}
