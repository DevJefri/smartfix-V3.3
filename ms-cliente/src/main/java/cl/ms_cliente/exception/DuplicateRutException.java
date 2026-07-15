package cl.ms_cliente.exception;

public class DuplicateRutException extends RuntimeException {
    public DuplicateRutException(String message) {
        super(message);
    }
}
