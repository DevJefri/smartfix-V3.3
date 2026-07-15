package cl.bff_web.client;

import cl.bff_web.dto.ConversionBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

/** Cliente HTTP hacia ms-conversor. */
@Component
public class ConversorClient {

    private final RestClient restClient;
    private final String baseUrl;

    public ConversorClient(RestClient restClient,
                            @Value("${services.conversor.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public ConversionBffResponseDTO convertir(BigDecimal monto, String moneda, String token) {
        return restClient.get()
                .uri(baseUrl + "/api/conversor/convertir?monto={monto}&moneda={moneda}", monto, moneda)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(ConversionBffResponseDTO.class);
    }
}
