package cl.bff_web.dto;

public record NotificationBffRequestDTO(
    String destinatario,
    String asunto,
    String mensaje,
    String canal
) {}
