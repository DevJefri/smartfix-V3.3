package cl.ms_auditoria.repository;

import cl.ms_auditoria.model.EventoAuditoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class EventoAuditoriaRepositoryTest {

    @Autowired
    private EventoAuditoriaRepository repository;

    @Test
    void findByServicioOrigenOrderByFechaDesc_shouldReturnOnlyMatching() {
        EventoAuditoria e1 = new EventoAuditoria();
        e1.setServicioOrigen("ms-inventario");
        e1.setAccion("AJUSTAR_STOCK");
        e1.setFecha(LocalDateTime.now());
        repository.save(e1);

        EventoAuditoria e2 = new EventoAuditoria();
        e2.setServicioOrigen("ms-factura");
        e2.setAccion("EMITIR_FACTURA");
        e2.setFecha(LocalDateTime.now());
        repository.save(e2);

        var result = repository.findByServicioOrigenOrderByFechaDesc("ms-inventario");

        assertEquals(1, result.size());
        assertEquals("AJUSTAR_STOCK", result.get(0).getAccion());
    }
}
