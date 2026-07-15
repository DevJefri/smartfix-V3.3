package cl.ms_conversor.service;

import cl.ms_conversor.dto.ConversionResponseDTO;
import cl.ms_conversor.exception.MonedaNoSoportadaException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * Convierte montos en CLP usando una tabla fija de tasas de cambio.
 * En un escenario productivo esta tabla podria refrescarse periodicamente
 * desde un proveedor externo; para mantener el microservicio pequeno y
 * sin dependencias externas, se usa una tabla configurada en memoria.
 */
@Service
public class ConversorServiceImpl implements ConversorService {

    private static final Map<String, BigDecimal> TASAS_DESDE_CLP = Map.of(
            "USD", new BigDecimal("0.00110"),
            "EUR", new BigDecimal("0.00095"),
            "ARS", new BigDecimal("1.15000")
    );

    @Override
    public ConversionResponseDTO convertir(BigDecimal monto, String monedaDestino) {
        String moneda = monedaDestino.toUpperCase();
        BigDecimal tasa = TASAS_DESDE_CLP.get(moneda);

        if (tasa == null) {
            throw new MonedaNoSoportadaException(monedaDestino);
        }

        BigDecimal montoConvertido = monto.multiply(tasa).setScale(2, RoundingMode.HALF_UP);

        return new ConversionResponseDTO(monto, "CLP", moneda, tasa, montoConvertido);
    }
}
