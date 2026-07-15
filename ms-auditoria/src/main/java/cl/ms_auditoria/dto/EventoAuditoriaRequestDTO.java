package cl.ms_auditoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EventoAuditoriaRequestDTO(

    @NotBlank(message = "El servicio de origen es obligatorio")
    String servicioOrigen,

    @NotBlank(message = "La accion es obligatoria")
    String accion,

    @Size(max = 500, message = "El detalle no puede superar los 500 caracteres")
    String detalle
) {}
