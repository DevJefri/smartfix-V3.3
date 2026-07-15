package cl.ms_diagnostico.controller;

import cl.ms_diagnostico.dto.DiagnosticoRequestDTO;
import cl.ms_diagnostico.dto.DiagnosticoResponseDTO;
import cl.ms_diagnostico.service.DiagnosticoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiagnosticoControllerTest {

    @Mock
    private DiagnosticoService diagnosticoService;

    @Test
    void analizar_shouldReturnOk() {
        DiagnosticoController controller = new DiagnosticoController(diagnosticoService);
        DiagnosticoResponseDTO responseDto = new DiagnosticoResponseDTO(
                "CELULAR", "ALTA", 48, "Atencion prioritaria");
        when(diagnosticoService.analizar(any())).thenReturn(responseDto);

        ResponseEntity<DiagnosticoResponseDTO> result = controller.analizar(
                new DiagnosticoRequestDTO(List.of("no enciende"), "CELULAR"));

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("ALTA", result.getBody().prioridad());
    }
}
