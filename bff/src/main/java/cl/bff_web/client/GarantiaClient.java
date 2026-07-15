package cl.bff_web.client;

import cl.bff_web.dto.GarantiaBffRequestDTO;
import cl.bff_web.dto.GarantiaBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/** Cliente HTTP hacia ms-garantia. */
@Component
public class GarantiaClient {

    private final RestClient restClient;
    private final String baseUrl;

    public GarantiaClient(RestClient restClient,
                           @Value("${services.garantia.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public GarantiaBffResponseDTO calcular(GarantiaBffRequestDTO request, String token) {
        return restClient.post()
                .uri(baseUrl + "/api/garantias/calcular")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(GarantiaBffResponseDTO.class);
    }
}
