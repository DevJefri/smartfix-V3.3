package cl.ms_diagnostico.service;

import cl.ms_diagnostico.dto.DiagnosticoRequestDTO;
import cl.ms_diagnostico.dto.DiagnosticoResponseDTO;
import cl.ms_diagnostico.exception.DispositivoNoSoportadoException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiagnosticoServiceImplTest {

    private final DiagnosticoServiceImpl service = new DiagnosticoServiceImpl();

    @Test
    void analizar_shouldReturnAltaPrioridadForWaterDamage() {
        DiagnosticoResponseDTO result = service.analizar(
                new DiagnosticoRequestDTO(List.of("Se le cayo agua encima"), "CELULAR"));

        assertEquals("ALTA", result.prioridad());
        assertEquals(48, result.tiempoEstimadoHoras());
    }

    @Test
    void analizar_shouldReturnMediaPrioridadForScreenIssue() {
        DiagnosticoResponseDTO result = service.analizar(
                new DiagnosticoRequestDTO(List.of("La pantalla esta rota"), "CELULAR"));

        assertEquals("MEDIA", result.prioridad());
        assertEquals(24, result.tiempoEstimadoHoras());
    }

    @Test
    void analizar_shouldApplyComplexityFactorForNotebook() {
        DiagnosticoResponseDTO result = service.analizar(
                new DiagnosticoRequestDTO(List.of("La pantalla esta rota"), "notebook"));

        // 24 horas * 1.3 = 31.2 -> redondeado a 31
        assertEquals(31, result.tiempoEstimadoHoras());
    }

    @Test
    void analizar_shouldThrowWhenDispositivoNoSoportado() {
        assertThrows(DispositivoNoSoportadoException.class, () ->
                service.analizar(new DiagnosticoRequestDTO(List.of("no enciende"), "SMARTWATCH")));
    }
}
