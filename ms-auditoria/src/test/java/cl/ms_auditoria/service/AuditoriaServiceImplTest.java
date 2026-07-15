package cl.ms_auditoria.service;

import cl.ms_auditoria.dto.EventoAuditoriaRequestDTO;
import cl.ms_auditoria.dto.EventoAuditoriaResponseDTO;
import cl.ms_auditoria.exception.ServicioOrigenInvalidoException;
import cl.ms_auditoria.model.EventoAuditoria;
import cl.ms_auditoria.repository.EventoAuditoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditoriaServiceImplTest {

    @Mock
    private EventoAuditoriaRepository eventoAuditoriaRepository;

    @Test
    void registrar_shouldSaveEvento() {
        AuditoriaServiceImpl service = new AuditoriaServiceImpl(eventoAuditoriaRepository);
        when(eventoAuditoriaRepository.save(any(EventoAuditoria.class))).thenAnswer(inv -> {
            EventoAuditoria e = inv.getArgument(0);
            return e;
        });

        EventoAuditoriaResponseDTO result = service.registrar(
                new EventoAuditoriaRequestDTO("ms-reparacion", "CREAR_REPARACION", "id=10"));

        assertEquals("ms-reparacion", result.servicioOrigen());
        assertEquals("CREAR_REPARACION", result.accion());
    }

    @Test
    void listarPorServicio_shouldThrowWhenServicioIsBlank() {
        AuditoriaServiceImpl service = new AuditoriaServiceImpl(eventoAuditoriaRepository);

        assertThrows(ServicioOrigenInvalidoException.class, () -> service.listarPorServicio("  "));
    }

    @Test
    void listarPorServicio_shouldReturnEventosDelServicio() {
        AuditoriaServiceImpl service = new AuditoriaServiceImpl(eventoAuditoriaRepository);
        EventoAuditoria evento = new EventoAuditoria();
        evento.setServicioOrigen("ms-factura");
        evento.setAccion("EMITIR_FACTURA");
        when(eventoAuditoriaRepository.findByServicioOrigenOrderByFechaDesc("ms-factura"))
                .thenReturn(List.of(evento));

        List<EventoAuditoriaResponseDTO> result = service.listarPorServicio("ms-factura");

        assertEquals(1, result.size());
        assertEquals("EMITIR_FACTURA", result.get(0).accion());
    }
}
