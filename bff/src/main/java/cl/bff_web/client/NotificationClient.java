package cl.bff_web.client;

import cl.bff_web.dto.NotificationBffRequestDTO;
import cl.bff_web.dto.NotificationBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/** Cliente HTTP hacia ms-notificacion. */
@Component
public class NotificationClient {

    private final RestClient restClient;
    private final String baseUrl;

    public NotificationClient(RestClient restClient,
                               @Value("${services.notificacion.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public NotificationBffResponseDTO enviar(NotificationBffRequestDTO request, String token) {
        return restClient.post()
                .uri(baseUrl + "/api/notificaciones/enviar")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(NotificationBffResponseDTO.class);
    }
}
