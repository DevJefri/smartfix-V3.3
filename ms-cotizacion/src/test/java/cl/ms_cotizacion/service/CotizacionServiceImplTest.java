package cl.ms_cotizacion.service;

import cl.ms_cotizacion.dto.CotizacionRequestDTO;
import cl.ms_cotizacion.dto.CotizacionResponseDTO;
import cl.ms_cotizacion.exception.CotizacionInvalidaException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CotizacionServiceImplTest {

    private final CotizacionServiceImpl service = new CotizacionServiceImpl();

    @Test
    void calcular_shouldReturnBaseAmountWithoutUrgencyCharge() {
        CotizacionResponseDTO result = service.calcular(
                new CotizacionRequestDTO("CELULAR", "BATERIA", false));

        assertEquals(new BigDecimal("20000"), result.montoBase());
        assertEquals(BigDecimal.ZERO, result.recargoUrgencia());
        assertEquals(new BigDecimal("20000"), result.montoTotal());
    }

    @Test
    void calcular_shouldAddUrgencyChargeWhenUrgente() {
        CotizacionResponseDTO result = service.calcular(
                new CotizacionRequestDTO("notebook", "pantalla", true));

        // base = 35000 * 1.5 = 52500 ; recargo = 52500 * 0.20 = 10500
        assertEquals(new BigDecimal("52500"), result.montoBase());
        assertEquals(new BigDecimal("10500"), result.recargoUrgencia());
        assertEquals(new BigDecimal("63000"), result.montoTotal());
    }

    @Test
    void calcular_shouldThrowWhenDispositivoNoSoportado() {
        assertThrows(CotizacionInvalidaException.class, () ->
                service.calcular(new CotizacionRequestDTO("SMARTWATCH", "BATERIA", false)));
    }

    @Test
    void calcular_shouldThrowWhenFallaNoSoportada() {
        assertThrows(CotizacionInvalidaException.class, () ->
                service.calcular(new CotizacionRequestDTO("CELULAR", "AGUA", false)));
    }
}
