package cl.ms_inventario.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String sku, int stockActual, int cantidadSolicitada) {
        super("Stock insuficiente para el repuesto '" + sku + "'. Stock actual: " +
                stockActual + ", cantidad solicitada: " + cantidadSolicitada);
    }
}
