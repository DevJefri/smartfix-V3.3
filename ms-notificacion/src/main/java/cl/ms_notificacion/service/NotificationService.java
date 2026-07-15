package cl.ms_notificacion.service;

import cl.ms_notificacion.dto.NotificationRequestDTO;
import cl.ms_notificacion.dto.NotificationResponseDTO;

public interface NotificationService {

    /**
     * Procesa el envio de una notificacion.
     *
     * @param dto datos del destinatario, canal y contenido
     * @return confirmacion con id generado, estado y fecha de envio
     */
    NotificationResponseDTO enviar(NotificationRequestDTO dto);
}
