package cl.ms_notificacion.dto;

import java.time.LocalDateTime;

/** Confirmacion de que la notificacion fue procesada (simulada). */
public record NotificationResponseDTO(
    String id,
    String destinatario,
    String canal,
    String estado,
    LocalDateTime fechaEnvio
) {}
