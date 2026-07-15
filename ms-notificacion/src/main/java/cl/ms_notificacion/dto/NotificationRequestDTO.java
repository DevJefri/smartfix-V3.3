package cl.ms_notificacion.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Datos de entrada para solicitar el envio de una notificacion.
 * El canal se valida como texto libre y se normaliza en el service
 * para poder devolver un error de negocio claro (CanalNoSoportadoException)
 * en lugar de un error generico de deserializacion si llega un valor invalido.
 */
public record NotificationRequestDTO(

    @NotBlank(message = "El destinatario es obligatorio")
    String destinatario,

    @NotBlank(message = "El asunto es obligatorio")
    String asunto,

    @NotBlank(message = "El mensaje es obligatorio")
    String mensaje,

    @NotBlank(message = "El canal es obligatorio (EMAIL o SMS)")
    String canal
) {}
