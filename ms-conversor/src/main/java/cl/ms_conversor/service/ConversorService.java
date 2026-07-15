package cl.ms_conversor.service;

import cl.ms_conversor.dto.ConversionResponseDTO;

import java.math.BigDecimal;

public interface ConversorService {

    /**
     * Convierte un monto en pesos chilenos (CLP) a la moneda solicitada.
     *
     * @param monto monto en CLP a convertir (debe ser mayor o igual a 0)
     * @param monedaDestino codigo de la moneda destino (USD, EUR, ARS)
     * @return detalle de la conversion, incluyendo la tasa utilizada
     */
    ConversionResponseDTO convertir(BigDecimal monto, String monedaDestino);
}
