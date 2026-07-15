package cl.ms_cotizacion.service;

import cl.ms_cotizacion.dto.CotizacionRequestDTO;
import cl.ms_cotizacion.dto.CotizacionResponseDTO;

public interface CotizacionService {

    /**
     * Calcula el presupuesto estimado de una reparacion.
     *
     * @param dto tipo de dispositivo, tipo de falla y si es urgente
     * @return desglose del monto base, el recargo por urgencia y el total
     */
    CotizacionResponseDTO calcular(CotizacionRequestDTO dto);
}
