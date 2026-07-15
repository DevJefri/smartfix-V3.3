package cl.ms_conversor.exception;

/** Se lanza cuando se solicita convertir a una moneda que no esta en la tabla de tasas. */
public class MonedaNoSoportadaException extends RuntimeException {
    public MonedaNoSoportadaException(String moneda) {
        super("Moneda no soportada: '" + moneda + "'");
    }
}
