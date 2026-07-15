package cl.bff_web.client;

import cl.bff_web.dto.FacturaBffRequestDTO;
import cl.bff_web.dto.FacturaBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/** Cliente HTTP hacia ms-factura. */
@Component
public class FacturaClient {

    private final RestClient restClient;
    private final String baseUrl;

    public FacturaClient(RestClient restClient,
                          @Value("${services.factura.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public FacturaBffResponseDTO emitir(FacturaBffRequestDTO request, String token) {
        return restClient.post()
                .uri(baseUrl + "/api/facturas")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(FacturaBffResponseDTO.class);
    }

    public FacturaBffResponseDTO obtener(String folio, String token) {
        return restClient.get()
                .uri(baseUrl + "/api/facturas/{folio}", folio)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(FacturaBffResponseDTO.class);
    }

    public List<FacturaBffResponseDTO> listarPorCliente(String rut, String token) {
        return restClient.get()
                .uri(baseUrl + "/api/facturas/cliente/{rut}", rut)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<FacturaBffResponseDTO>>() {});
    }

    public FacturaBffResponseDTO anular(String folio, String token) {
        return restClient.patch()
                .uri(baseUrl + "/api/facturas/{folio}/anular", folio)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(FacturaBffResponseDTO.class);
    }
}
