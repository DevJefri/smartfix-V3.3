package cl.ms_garantia.service;

import cl.ms_garantia.dto.GarantiaRequestDTO;
import cl.ms_garantia.dto.GarantiaResponseDTO;
import cl.ms_garantia.exception.TipoReparacionInvalidoException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Calcula el vencimiento de garantia sumando a la fecha de entrega la
 * cantidad de dias que corresponde segun el tipo de reparacion realizada.
 */
@Service
public class GarantiaServiceImpl implements GarantiaService {

    private static final Map<String, Integer> DIAS_GARANTIA_POR_TIPO = Map.of(
            "PANTALLA", 90,
            "BATERIA", 180,
            "SOFTWARE", 30,
            "OTRO", 60
    );

    @Override
    public GarantiaResponseDTO calcular(GarantiaRequestDTO dto) {
        String tipo = dto.tipoReparacion().toUpperCase();

        Integer dias = DIAS_GARANTIA_POR_TIPO.get(tipo);
        if (dias == null) {
            throw new TipoReparacionInvalidoException(dto.tipoReparacion());
        }

        return new GarantiaResponseDTO(
                tipo,
                dto.fechaEntrega(),
                dto.fechaEntrega().plusDays(dias),
                dias
        );
    }
}
