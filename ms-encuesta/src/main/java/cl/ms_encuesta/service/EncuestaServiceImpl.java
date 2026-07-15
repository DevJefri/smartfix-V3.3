package cl.ms_encuesta.service;

import cl.ms_encuesta.dto.EncuestaRequestDTO;
import cl.ms_encuesta.dto.EncuestaResponseDTO;
import cl.ms_encuesta.dto.PromedioResponseDTO;
import cl.ms_encuesta.exception.EncuestaDuplicadaException;
import cl.ms_encuesta.exception.EncuestaNotFoundException;
import cl.ms_encuesta.model.Encuesta;
import cl.ms_encuesta.repository.EncuestaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EncuestaServiceImpl implements EncuestaService {

    private final EncuestaRepository encuestaRepository;

    public EncuestaServiceImpl(EncuestaRepository encuestaRepository) {
        this.encuestaRepository = encuestaRepository;
    }

    @Override
    public EncuestaResponseDTO registrar(EncuestaRequestDTO dto) {
        if (encuestaRepository.existsByReparacionId(dto.reparacionId())) {
            throw new EncuestaDuplicadaException(dto.reparacionId());
        }

        Encuesta encuesta = new Encuesta();
        encuesta.setReparacionId(dto.reparacionId());
        encuesta.setRutCliente(dto.rutCliente());
        encuesta.setPuntuacion(dto.puntuacion());
        encuesta.setComentario(dto.comentario());
        encuesta.setFecha(LocalDateTime.now());

        return mapToDto(encuestaRepository.save(encuesta));
    }

    @Override
    public EncuestaResponseDTO obtenerPorReparacion(Long reparacionId) {
        Encuesta encuesta = encuestaRepository.findByReparacionId(reparacionId)
                .orElseThrow(() -> new EncuestaNotFoundException(reparacionId));
        return mapToDto(encuesta);
    }

    @Override
    public PromedioResponseDTO calcularPromedio() {
        Double promedio = encuestaRepository.calcularPromedioPuntuacion();
        long total = encuestaRepository.count();
        return new PromedioResponseDTO(promedio == null ? 0.0 : promedio, total);
    }

    private EncuestaResponseDTO mapToDto(Encuesta e) {
        return new EncuestaResponseDTO(
                e.getReparacionId(), e.getRutCliente(), e.getPuntuacion(), e.getComentario(), e.getFecha());
    }
}
