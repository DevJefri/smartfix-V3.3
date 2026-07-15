package cl.ms_diagnostico.service;

import cl.ms_diagnostico.dto.DiagnosticoRequestDTO;
import cl.ms_diagnostico.dto.DiagnosticoResponseDTO;
import cl.ms_diagnostico.exception.DispositivoNoSoportadoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Reglas simples de triage: primero determina una prioridad y un tiempo
 * base segun palabras clave presentes en los sintomas, luego ajusta ese
 * tiempo con un factor de complejidad segun el tipo de dispositivo.
 */
@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {

    private static final Set<String> PALABRAS_ALTA_PRIORIDAD =
            Set.of("no enciende", "agua", "liquido", "humo", "no carga");
    private static final Set<String> PALABRAS_MEDIA_PRIORIDAD =
            Set.of("pantalla", "bateria", "carga lenta");

    private static final Map<String, Double> FACTOR_COMPLEJIDAD_POR_DISPOSITIVO = Map.of(
            "CELULAR", 1.0,
            "TABLET", 1.1,
            "CONSOLA", 1.2,
            "NOTEBOOK", 1.3
    );

    @Override
    public DiagnosticoResponseDTO analizar(DiagnosticoRequestDTO dto) {
        String dispositivo = dto.tipoDispositivo().toUpperCase();
        Double factor = FACTOR_COMPLEJIDAD_POR_DISPOSITIVO.get(dispositivo);
        if (factor == null) {
            throw new DispositivoNoSoportadoException(dto.tipoDispositivo());
        }

        List<String> sintomasEnMinuscula = dto.sintomas().stream().map(String::toLowerCase).toList();

        String prioridad;
        int horasBase;
        if (contieneAlguna(sintomasEnMinuscula, PALABRAS_ALTA_PRIORIDAD)) {
            prioridad = "ALTA";
            horasBase = 48;
        } else if (contieneAlguna(sintomasEnMinuscula, PALABRAS_MEDIA_PRIORIDAD)) {
            prioridad = "MEDIA";
            horasBase = 24;
        } else {
            prioridad = "BAJA";
            horasBase = 12;
        }

        // Diagnosticos con muchos sintomas reportados suelen tomar mas tiempo de revision.
        int horasPorCantidadDeSintomas = dto.sintomas().size() > 2 ? 6 : 0;

        int tiempoEstimado = (int) Math.round(
                (horasBase + horasPorCantidadDeSintomas) * factor);

        String recomendacion = switch (prioridad) {
            case "ALTA" -> "Atencion prioritaria: posible dano grave, se recomienda diagnostico presencial inmediato.";
            case "MEDIA" -> "Atencion estandar: agendar revision en los proximos dias.";
            default -> "Sin urgencia: se puede coordinar una revision de rutina.";
        };

        return new DiagnosticoResponseDTO(dispositivo, prioridad, tiempoEstimado, recomendacion);
    }

    private boolean contieneAlguna(List<String> sintomas, Set<String> palabrasClave) {
        return sintomas.stream().anyMatch(sintoma ->
                palabrasClave.stream().anyMatch(sintoma::contains));
    }
}
