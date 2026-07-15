package cl.bff_web.client;

import cl.bff_web.dto.EventoAuditoriaBffRequestDTO;
import cl.bff_web.dto.EventoAuditoriaBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/** Cliente HTTP hacia ms-auditoria. */
@Component
public class AuditoriaClient {

    private final RestClient restClient;
    private final String baseUrl;

    public AuditoriaClient(RestClient restClient,
                            @Value("${services.auditoria.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public EventoAuditoriaBffResponseDTO registrar(EventoAuditoriaBffRequestDTO request, String token) {
        return restClient.post()
                .uri(baseUrl + "/api/auditoria/registrar")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(EventoAuditoriaBffResponseDTO.class);
    }

    public List<EventoAuditoriaBffResponseDTO> listarTodos(String token) {
        return restClient.get()
                .uri(baseUrl + "/api/auditoria")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<EventoAuditoriaBffResponseDTO>>() {});
    }

    public List<EventoAuditoriaBffResponseDTO> listarPorServicio(String nombre, String token) {
        return restClient.get()
                .uri(baseUrl + "/api/auditoria/servicio/{nombre}", nombre)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<EventoAuditoriaBffResponseDTO>>() {});
    }
}
