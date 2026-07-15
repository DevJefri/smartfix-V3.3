package cl.ms_inventario.service;

import cl.ms_inventario.dto.RepuestoRequestDTO;
import cl.ms_inventario.dto.RepuestoResponseDTO;

import java.util.List;

public interface RepuestoService {

    /** Registra un nuevo repuesto en el inventario. */
    RepuestoResponseDTO crear(RepuestoRequestDTO dto);

    /** Lista todos los repuestos registrados. */
    List<RepuestoResponseDTO> listarTodos();

    /** Obtiene un repuesto por su SKU. */
    RepuestoResponseDTO obtenerPorSku(String sku);

    /** Actualiza el nombre y precio de un repuesto existente. */
    RepuestoResponseDTO actualizar(String sku, RepuestoRequestDTO dto);

    /** Elimina un repuesto del inventario. */
    void eliminar(String sku);

    /**
     * Ajusta el stock de un repuesto (positivo para ingresar, negativo para
     * descontar por uso en una reparacion).
     *
     * @param sku SKU del repuesto
     * @param cantidad delta a aplicar sobre el stock actual
     * @return repuesto con el stock actualizado
     */
    RepuestoResponseDTO ajustarStock(String sku, int cantidad);
}
