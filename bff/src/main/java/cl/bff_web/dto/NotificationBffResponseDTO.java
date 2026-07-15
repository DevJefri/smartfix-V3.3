package cl.bff_web.dto;

public record NotificationBffResponseDTO(
    String id,
    String destinatario,
    String canal,
    String estado,
    String fechaEnvio
) {}
