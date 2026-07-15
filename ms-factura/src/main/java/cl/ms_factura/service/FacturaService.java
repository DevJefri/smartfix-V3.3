package cl.ms_factura.service;

import cl.ms_factura.dto.FacturaRequestDTO;
import cl.ms_factura.dto.FacturaResponseDTO;

import java.util.List;

public interface FacturaService {

    /** Emite una nueva factura para una reparacion finalizada. */
    FacturaResponseDTO emitir(FacturaRequestDTO dto);

    /** Busca una factura por su folio. */
    FacturaResponseDTO obtenerPorFolio(String folio);

    /** Lista las facturas emitidas a un cliente. */
    List<FacturaResponseDTO> listarPorCliente(String rutCliente);

    /** Anula una factura previamente emitida. */
    FacturaResponseDTO anular(String folio);
}
