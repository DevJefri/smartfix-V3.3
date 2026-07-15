package cl.ms_factura.exception;

public class FacturaNotFoundException extends RuntimeException {
    public FacturaNotFoundException(String folio) {
        super("No se encontro una factura con folio: " + folio);
    }
}
