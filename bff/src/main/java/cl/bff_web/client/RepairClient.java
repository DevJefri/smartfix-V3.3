package cl.bff_web.client;

import cl.bff_web.dto.RepairBffDTO;
import cl.bff_web.dto.RepairBffRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class RepairClient {

    private final RestClient restClient;
    private final String repairBaseUrl;

    public RepairClient(RestClient restClient,
                        @Value("${services.reparacion.url}") String repairBaseUrl) {
        this.restClient = restClient;
        this.repairBaseUrl = repairBaseUrl;
    }

    public List<RepairBffDTO> listByRut(String rut, String token) {
        return restClient.get()
                .uri(repairBaseUrl + "/api/reparaciones/cliente/{rut}", rut)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<RepairBffDTO>>() {});
    }

    public RepairBffDTO crear(RepairBffRequestDTO request, String token) {
        return restClient.post()
                .uri(repairBaseUrl + "/api/reparaciones")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(request)
                .retrieve()
                .body(RepairBffDTO.class);
    }

    public List<RepairBffDTO> listarTodas(String token) {
        return restClient.get()
                .uri(repairBaseUrl + "/api/reparaciones")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<RepairBffDTO>>() {});
    }

    public RepairBffDTO actualizarEstado(Long id, String nuevoEstado, String token) {
        return restClient.patch()
                .uri(repairBaseUrl + "/api/reparaciones/{id}/estado?newStatus={estado}", id, nuevoEstado)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(RepairBffDTO.class);
    }
}
