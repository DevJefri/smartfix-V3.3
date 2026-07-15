package cl.ms_factura.exception;

public class FacturaYaAnuladaException extends RuntimeException {
    public FacturaYaAnuladaException(String folio) {
        super("La factura '" + folio + "' ya se encuentra anulada");
    }
}
