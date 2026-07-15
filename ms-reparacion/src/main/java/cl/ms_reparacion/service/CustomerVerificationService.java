package cl.ms_reparacion.service;

import cl.ms_reparacion.exception.CustomerNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class CustomerVerificationService {

    private final RestTemplate restTemplate;
    private final SecretKey signingKey;

    @Value("${services.cliente.url}")
    private String customerBaseUrl;

    public CustomerVerificationService(@Value("${jwt.secret}") String jwtSecret) {
        this.restTemplate = new RestTemplate();
        this.signingKey = Keys.hmacShaKeyFor(sha256(jwtSecret));
    }

    private byte[] sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(value.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not initialize SHA-256", e);
        }
    }

    private String generateInternalToken() {
        return Jwts.builder()
                .subject("ms-reparacion")
                .claim("role", "INTERNAL_SERVICE")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60_000))
                .signWith(signingKey)
                .compact();
    }

    public void verifyCustomerExists(String rut) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + generateInternalToken());
            HttpEntity<Void> request = new HttpEntity<>(headers);

            restTemplate.exchange(
                customerBaseUrl + "/api/customers/" + rut,
                HttpMethod.GET,
                request,
                Object.class
            );
        } catch (HttpClientErrorException.NotFound ex) {
            throw new CustomerNotFoundException(
                "No customer found with RUT " + rut + ". Please register the customer first.");
        } catch (HttpClientErrorException | ResourceAccessException ex) {
            throw new CustomerNotFoundException(
                "Could not verify the customer. The customer service is unavailable.");
        }
    }
}
