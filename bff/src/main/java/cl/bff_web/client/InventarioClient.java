package cl.bff_web.client;

import cl.bff_web.dto.RepuestoBffRequestDTO;
import cl.bff_web.dto.RepuestoBffResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/** Cliente HTTP hacia ms-inventario. */
@Component
public class InventarioClient {

    private final RestClient restClient;
    private final String baseUrl;

    public InventarioClient(RestClient restClient,
                             @Value("${services.inventario.url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public RepuestoBffResponseDTO crear(RepuestoBffRequestDTO request, String token) {
        return restClient.post()
                .uri(baseUrl + "/api/repuestos")
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(RepuestoBffResponseDTO.class);
    }

    public List<RepuestoBffResponseDTO> listar(String token) {
        return restClient.get()
                .uri(baseUrl + "/api/repuestos")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<RepuestoBffResponseDTO>>() {});
    }

    public RepuestoBffResponseDTO obtener(String sku, String token) {
        return restClient.get()
                .uri(baseUrl + "/api/repuestos/{sku}", sku)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(RepuestoBffResponseDTO.class);
    }

    public RepuestoBffResponseDTO actualizar(String sku, RepuestoBffRequestDTO request, String token) {
        return restClient.put()
                .uri(baseUrl + "/api/repuestos/{sku}", sku)
                .header("Authorization", "Bearer " + token)
                .body(request)
                .retrieve()
                .body(RepuestoBffResponseDTO.class);
    }

    public void eliminar(String sku, String token) {
        restClient.delete()
                .uri(baseUrl + "/api/repuestos/{sku}", sku)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .toBodilessEntity();
    }

    public RepuestoBffResponseDTO ajustarStock(String sku, int cantidad, String token) {
        return restClient.patch()
                .uri(baseUrl + "/api/repuestos/{sku}/stock?cantidad={cantidad}", sku, cantidad)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(RepuestoBffResponseDTO.class);
    }
}
