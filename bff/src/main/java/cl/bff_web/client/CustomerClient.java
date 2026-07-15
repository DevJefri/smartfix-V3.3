package cl.bff_web.client;

import cl.bff_web.dto.CustomerBffDTO;
import cl.bff_web.dto.LoginRequestDTO;
import cl.bff_web.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class CustomerClient {

    private final RestClient restClient;
    private final String customerBaseUrl;

    public CustomerClient(RestClient restClient,
                          @Value("${services.cliente.url}") String customerBaseUrl) {
        this.restClient = restClient;
        this.customerBaseUrl = customerBaseUrl;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        return restClient.post()
                .uri(customerBaseUrl + "/api/auth/login")
                .body(request)
                .retrieve()
                .body(LoginResponseDTO.class);
    }

    public LoginResponseDTO register(LoginRequestDTO request) {
        return restClient.post()
                .uri(customerBaseUrl + "/api/auth/register")
                .body(request)
                .retrieve()
                .body(LoginResponseDTO.class);
    }

    public CustomerBffDTO getByRut(String rut, String token) {
        return restClient.get()
                .uri(customerBaseUrl + "/api/customers/{rut}", rut)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(CustomerBffDTO.class);
    }

    public CustomerBffDTO crear(CustomerBffDTO request, String token) {
        return restClient.post()
                .uri(customerBaseUrl + "/api/customers")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(request)
                .retrieve()
                .body(CustomerBffDTO.class);
    }

    public List<CustomerBffDTO> listar(String token) {
        return restClient.get()
                .uri(customerBaseUrl + "/api/customers")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CustomerBffDTO>>() {});
    }

    public CustomerBffDTO actualizar(String rut, CustomerBffDTO request, String token) {
        return restClient.put()
                .uri(customerBaseUrl + "/api/customers/{rut}", rut)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(request)
                .retrieve()
                .body(CustomerBffDTO.class);
    }

    public void eliminar(String rut, String token) {
        restClient.delete()
                .uri(customerBaseUrl + "/api/customers/{rut}", rut)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .toBodilessEntity();
    }
}
