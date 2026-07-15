package cl.ms_factura.controller;

import cl.ms_factura.dto.FacturaRequestDTO;
import cl.ms_factura.dto.FacturaResponseDTO;
import cl.ms_factura.service.FacturaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacturaControllerTest {

    @Mock
    private FacturaService facturaService;

    @Test
    void emitir_shouldReturnCreated() {
        FacturaController controller = new FacturaController(facturaService);
        FacturaResponseDTO responseDto = new FacturaResponseDTO(
                "F-000001", 1L, "12345678-9", new BigDecimal("25000"), "EMITIDA", LocalDateTime.now());
        when(facturaService.emitir(any())).thenReturn(responseDto);

        ResponseEntity<FacturaResponseDTO> result = controller.emitir(
                new FacturaRequestDTO(1L, "12345678-9", new BigDecimal("25000")));

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("F-000001", result.getBody().folio());
    }

    @Test
    void anular_shouldReturnOk() {
        FacturaController controller = new FacturaController(facturaService);
        FacturaResponseDTO responseDto = new FacturaResponseDTO(
                "F-000001", 1L, "12345678-9", new BigDecimal("25000"), "ANULADA", LocalDateTime.now());
        when(facturaService.anular("F-000001")).thenReturn(responseDto);

        ResponseEntity<FacturaResponseDTO> result = controller.anular("F-000001");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("ANULADA", result.getBody().estado());
    }
}
