package cl.ms_inventario.exception;

public class RepuestoNotFoundException extends RuntimeException {
    public RepuestoNotFoundException(String sku) {
        super("No se encontro un repuesto con SKU: " + sku);
    }
}
