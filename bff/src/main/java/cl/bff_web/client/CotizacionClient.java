package cl.bff_web.client;

import cl.bff_web.dto.CotizacionBffRequestDTO;
import cl.bff_web.dto.CotizacionBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/** Cliente HTTP hacia ms-cotizacion. */
@Component
public class CotizacionClient {

    private final RestClient restClient;
    private final String baseUrl;

    public CotizacionClient(RestClient restClient,
                             @Value("${services.cotizacion.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public CotizacionBffResponseDTO calcular(CotizacionBffRequestDTO request, String token) {
        return restClient.post()
                .uri(baseUrl + "/api/cotizaciones/calcular")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(CotizacionBffResponseDTO.class);
    }
}
