package cl.ms_inventario.exception;

public class RepuestoDuplicadoException extends RuntimeException {
    public RepuestoDuplicadoException(String sku) {
        super("Ya existe un repuesto registrado con SKU: " + sku);
    }
}
