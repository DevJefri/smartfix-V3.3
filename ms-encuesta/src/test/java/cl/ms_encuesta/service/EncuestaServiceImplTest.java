package cl.ms_encuesta.service;

import cl.ms_encuesta.dto.EncuestaRequestDTO;
import cl.ms_encuesta.dto.EncuestaResponseDTO;
import cl.ms_encuesta.dto.PromedioResponseDTO;
import cl.ms_encuesta.exception.EncuestaDuplicadaException;
import cl.ms_encuesta.exception.EncuestaNotFoundException;
import cl.ms_encuesta.model.Encuesta;
import cl.ms_encuesta.repository.EncuestaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EncuestaServiceImplTest {

    @Mock
    private EncuestaRepository encuestaRepository;

    @Test
    void registrar_shouldThrowWhenDuplicada() {
        EncuestaServiceImpl service = new EncuestaServiceImpl(encuestaRepository);
        when(encuestaRepository.existsByReparacionId(1L)).thenReturn(true);

        assertThrows(EncuestaDuplicadaException.class, () ->
                service.registrar(new EncuestaRequestDTO(1L, "12345678-9", 5, "Excelente")));
    }

    @Test
    void registrar_shouldSaveWhenNoDuplicada() {
        EncuestaServiceImpl service = new EncuestaServiceImpl(encuestaRepository);
        when(encuestaRepository.existsByReparacionId(2L)).thenReturn(false);
        when(encuestaRepository.save(any(Encuesta.class))).thenAnswer(inv -> inv.getArgument(0));

        EncuestaResponseDTO result = service.registrar(
                new EncuestaRequestDTO(2L, "12345678-9", 4, "Buena atencion"));

        assertEquals(4, result.puntuacion());
    }

    @Test
    void obtenerPorReparacion_shouldThrowWhenNotFound() {
        EncuestaServiceImpl service = new EncuestaServiceImpl(encuestaRepository);
        when(encuestaRepository.findByReparacionId(99L)).thenReturn(Optional.empty());

        assertThrows(EncuestaNotFoundException.class, () -> service.obtenerPorReparacion(99L));
    }

    @Test
    void calcularPromedio_shouldReturnZeroWhenNoEncuestas() {
        EncuestaServiceImpl service = new EncuestaServiceImpl(encuestaRepository);
        when(encuestaRepository.calcularPromedioPuntuacion()).thenReturn(null);
        when(encuestaRepository.count()).thenReturn(0L);

        PromedioResponseDTO result = service.calcularPromedio();

        assertEquals(0.0, result.promedio());
        assertEquals(0L, result.totalEncuestas());
    }
}
