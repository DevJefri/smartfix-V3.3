package cl.ms_garantia.service;

import cl.ms_garantia.dto.GarantiaRequestDTO;
import cl.ms_garantia.dto.GarantiaResponseDTO;

public interface GarantiaService {

    /**
     * Calcula la fecha de vencimiento de la garantia de una reparacion.
     *
     * @param dto fecha de entrega y tipo de reparacion realizada
     * @return fecha de vencimiento y cantidad de dias de garantia aplicados
     */
    GarantiaResponseDTO calcular(GarantiaRequestDTO dto);
}
