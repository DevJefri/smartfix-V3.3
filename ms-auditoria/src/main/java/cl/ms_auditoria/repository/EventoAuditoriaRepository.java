package cl.ms_auditoria.repository;

import cl.ms_auditoria.model.EventoAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoAuditoriaRepository extends JpaRepository<EventoAuditoria, Long> {
    List<EventoAuditoria> findByServicioOrigenOrderByFechaDesc(String servicioOrigen);
    List<EventoAuditoria> findAllByOrderByFechaDesc();
}
