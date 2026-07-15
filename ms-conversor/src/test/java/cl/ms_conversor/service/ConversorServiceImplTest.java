package cl.ms_conversor.service;

import cl.ms_conversor.dto.ConversionResponseDTO;
import cl.ms_conversor.exception.MonedaNoSoportadaException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ConversorServiceImplTest {

    private final ConversorServiceImpl service = new ConversorServiceImpl();

    @Test
    void convertir_shouldConvertClpToUsd() {
        ConversionResponseDTO result = service.convertir(new BigDecimal("100000"), "usd");

        assertEquals("USD", result.monedaDestino());
        assertEquals("CLP", result.monedaOrigen());
        assertEquals(new BigDecimal("110.00"), result.montoConvertido());
    }

    @Test
    void convertir_shouldThrowWhenMonedaNoSoportada() {
        assertThrows(MonedaNoSoportadaException.class, () ->
                service.convertir(new BigDecimal("1000"), "JPY"));
    }
}
