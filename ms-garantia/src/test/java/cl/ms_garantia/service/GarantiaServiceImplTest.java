package cl.ms_garantia.service;

import cl.ms_garantia.dto.GarantiaRequestDTO;
import cl.ms_garantia.dto.GarantiaResponseDTO;
import cl.ms_garantia.exception.TipoReparacionInvalidoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GarantiaServiceImplTest {

    private final GarantiaServiceImpl service = new GarantiaServiceImpl();

    @Test
    void calcular_shouldAdd90DaysForPantalla() {
        LocalDate entrega = LocalDate.of(2026, 1, 1);
        GarantiaResponseDTO result = service.calcular(new GarantiaRequestDTO(entrega, "pantalla"));

        assertEquals(90, result.diasGarantia());
        assertEquals(LocalDate.of(2026, 4, 1), result.fechaVencimiento());
    }

    @Test
    void calcular_shouldAdd180DaysForBateria() {
        LocalDate entrega = LocalDate.of(2026, 1, 1);
        GarantiaResponseDTO result = service.calcular(new GarantiaRequestDTO(entrega, "BATERIA"));

        assertEquals(180, result.diasGarantia());
    }

    @Test
    void calcular_shouldThrowWhenTipoNoSoportado() {
        assertThrows(TipoReparacionInvalidoException.class, () ->
                service.calcular(new GarantiaRequestDTO(LocalDate.now(), "CARCASA")));
    }
}
