package cl.ms_auditoria.service;

import cl.ms_auditoria.dto.EventoAuditoriaRequestDTO;
import cl.ms_auditoria.dto.EventoAuditoriaResponseDTO;

import java.util.List;

public interface AuditoriaService {

    /** Registra un nuevo evento en la bitacora. */
    EventoAuditoriaResponseDTO registrar(EventoAuditoriaRequestDTO dto);

    /** Lista todos los eventos registrados, del mas reciente al mas antiguo. */
    List<EventoAuditoriaResponseDTO> listarTodos();

    /** Lista los eventos registrados por un servicio en particular. */
    List<EventoAuditoriaResponseDTO> listarPorServicio(String servicioOrigen);
}
