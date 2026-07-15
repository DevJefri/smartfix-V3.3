package cl.ms_encuesta.service;

import cl.ms_encuesta.dto.EncuestaRequestDTO;
import cl.ms_encuesta.dto.EncuestaResponseDTO;
import cl.ms_encuesta.dto.PromedioResponseDTO;

public interface EncuestaService {

    /** Registra la encuesta de satisfaccion de una reparacion (una por reparacion). */
    EncuestaResponseDTO registrar(EncuestaRequestDTO dto);

    /** Obtiene la encuesta asociada a una reparacion. */
    EncuestaResponseDTO obtenerPorReparacion(Long reparacionId);

    /** Calcula el promedio de puntuacion de todas las encuestas registradas. */
    PromedioResponseDTO calcularPromedio();
}
