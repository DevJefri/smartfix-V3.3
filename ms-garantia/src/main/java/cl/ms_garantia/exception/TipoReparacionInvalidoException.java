package cl.ms_garantia.exception;

/** Se lanza cuando el tipo de reparacion no tiene una politica de garantia definida. */
public class TipoReparacionInvalidoException extends RuntimeException {
    public TipoReparacionInvalidoException(String tipoReparacion) {
        super("Tipo de reparacion no soportado para calculo de garantia: '" + tipoReparacion + "'");
    }
}
