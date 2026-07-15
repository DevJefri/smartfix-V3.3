package cl.bff_web.client;

import cl.bff_web.dto.DiagnosticoBffRequestDTO;
import cl.bff_web.dto.DiagnosticoBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/** Cliente HTTP hacia ms-diagnostico. */
@Component
public class DiagnosticoClient {

    private final RestClient restClient;
    private final String baseUrl;

    public DiagnosticoClient(RestClient restClient,
                              @Value("${services.diagnostico.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public DiagnosticoBffResponseDTO analizar(DiagnosticoBffRequestDTO request, String token) {
        return restClient.post()
                .uri(baseUrl + "/api/diagnostico/analizar")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(DiagnosticoBffResponseDTO.class);
    }
}
