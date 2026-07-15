package cl.bff_web.controller;

import cl.bff_web.dto.CotizacionBffRequestDTO;
import cl.bff_web.dto.CotizacionBffResponseDTO;
import cl.bff_web.service.BffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CotizacionBffControllerTest {

    @Mock
    private BffService bffService;

    @Test
    void calcular_shouldReturnBodyFromBffService() {
        CotizacionBffController controller = new CotizacionBffController(bffService);
        CotizacionBffResponseDTO expected = new CotizacionBffResponseDTO(
                "CELULAR", "BATERIA", false, new BigDecimal("20000"), BigDecimal.ZERO, new BigDecimal("20000"));
        when(bffService.calcularCotizacion(any(), any())).thenReturn(expected);

        CotizacionBffResponseDTO result = controller.calcular(
                new CotizacionBffRequestDTO("CELULAR", "BATERIA", false), "Bearer token123");

        assertEquals(expected, result);
    }
}
