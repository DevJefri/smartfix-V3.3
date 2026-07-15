package cl.bff_web.client;

import cl.bff_web.dto.EncuestaBffRequestDTO;
import cl.bff_web.dto.EncuestaBffResponseDTO;
import cl.bff_web.dto.PromedioBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/** Cliente HTTP hacia ms-encuesta. */
@Component
public class EncuestaClient {

    private final RestClient restClient;
    private final String baseUrl;

    public EncuestaClient(RestClient restClient,
                           @Value("${services.encuesta.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public EncuestaBffResponseDTO registrar(EncuestaBffRequestDTO request, String token) {
        return restClient.post()
                .uri(baseUrl + "/api/encuestas")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(EncuestaBffResponseDTO.class);
    }

    public EncuestaBffResponseDTO obtenerPorReparacion(Long reparacionId, String token) {
        return restClient.get()
                .uri(baseUrl + "/api/encuestas/reparacion/{id}", reparacionId)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(EncuestaBffResponseDTO.class);
    }

    public PromedioBffResponseDTO promedio(String token) {
        return restClient.get()
                .uri(baseUrl + "/api/encuestas/promedio")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(PromedioBffResponseDTO.class);
    }
}
