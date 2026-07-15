package cl.ms_cotizacion.exception;

/** Se lanza cuando el tipo de dispositivo o el tipo de falla no estan en las tablas soportadas. */
public class CotizacionInvalidaException extends RuntimeException {
    public CotizacionInvalidaException(String message) {
        super(message);
    }
}
