package cl.ms_cotizacion.service;

import cl.ms_cotizacion.dto.CotizacionRequestDTO;
import cl.ms_cotizacion.dto.CotizacionResponseDTO;
import cl.ms_cotizacion.exception.CotizacionInvalidaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * Calcula el presupuesto de una reparacion combinando dos tablas de
 * negocio (precio base por dispositivo y multiplicador por tipo de falla)
 * mas un recargo fijo del 20% si el cliente pide atencion urgente.
 */
@Service
public class CotizacionServiceImpl implements CotizacionService {

    private static final Logger log = LoggerFactory.getLogger(CotizacionServiceImpl.class);

    private static final Map<String, BigDecimal> PRECIO_BASE_POR_DISPOSITIVO = Map.of(
            "CELULAR", new BigDecimal("20000"),
            "NOTEBOOK", new BigDecimal("35000"),
            "TABLET", new BigDecimal("25000"),
            "CONSOLA", new BigDecimal("30000")
    );

    private static final Map<String, BigDecimal> MULTIPLICADOR_POR_FALLA = Map.of(
            "PANTALLA", new BigDecimal("1.5"),
            "BATERIA", new BigDecimal("1.0"),
            "SOFTWARE", new BigDecimal("0.8"),
            "OTRO", new BigDecimal("1.2")
    );

    private static final BigDecimal RECARGO_URGENCIA = new BigDecimal("0.20");

    @Override
    public CotizacionResponseDTO calcular(CotizacionRequestDTO dto) {
        String dispositivo = dto.tipoDispositivo().toUpperCase();
        String falla = dto.tipoFalla().toUpperCase();

        try {
            BigDecimal precioBase = PRECIO_BASE_POR_DISPOSITIVO.get(dispositivo);
            if (precioBase == null) {
                throw new CotizacionInvalidaException(
                        "Tipo de dispositivo no soportado: '" + dto.tipoDispositivo() +
                        "'. Valores validos: " + PRECIO_BASE_POR_DISPOSITIVO.keySet());
            }

            BigDecimal multiplicador = MULTIPLICADOR_POR_FALLA.get(falla);
            if (multiplicador == null) {
                throw new CotizacionInvalidaException(
                        "Tipo de falla no soportado: '" + dto.tipoFalla() +
                        "'. Valores validos: " + MULTIPLICADOR_POR_FALLA.keySet());
            }

            BigDecimal montoBase = precioBase.multiply(multiplicador).setScale(0, RoundingMode.HALF_UP);
            BigDecimal recargo = Boolean.TRUE.equals(dto.urgente())
                    ? montoBase.multiply(RECARGO_URGENCIA).setScale(0, RoundingMode.HALF_UP)
                    : BigDecimal.ZERO;
            BigDecimal total = montoBase.add(recargo);

            return new CotizacionResponseDTO(
                    dispositivo, falla, Boolean.TRUE.equals(dto.urgente()), montoBase, recargo, total);
        } catch (CotizacionInvalidaException ex) {
            log.warn("Cotizacion rechazada: {}", ex.getMessage());
            throw ex;
        }
    }
}
