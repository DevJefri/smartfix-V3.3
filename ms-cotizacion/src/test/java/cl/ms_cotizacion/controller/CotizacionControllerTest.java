package cl.ms_cotizacion.controller;

import cl.ms_cotizacion.dto.CotizacionRequestDTO;
import cl.ms_cotizacion.dto.CotizacionResponseDTO;
import cl.ms_cotizacion.service.CotizacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CotizacionControllerTest {

    @Mock
    private CotizacionService cotizacionService;

    @Test
    void calcular_shouldReturnOk() {
        CotizacionController controller = new CotizacionController(cotizacionService);
        CotizacionResponseDTO responseDto = new CotizacionResponseDTO(
                "CELULAR", "BATERIA", false, new BigDecimal("20000"), BigDecimal.ZERO, new BigDecimal("20000"));
        when(cotizacionService.calcular(any())).thenReturn(responseDto);

        ResponseEntity<CotizacionResponseDTO> result = controller.calcular(
                new CotizacionRequestDTO("CELULAR", "BATERIA", false));

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(new BigDecimal("20000"), result.getBody().montoTotal());
    }
}
