package cl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada del API Gateway de Smartfix.
 *
 * Regla de oro del proyecto: este Gateway es el UNICO punto de entrada
 * publico del sistema. Todo el trafico externo (puerto 8080) se reenvia
 * al BFF (bff-web, puerto interno 8083), que es a su vez el unico
 * componente autorizado a hablar directamente con los 11 microservicios
 * de negocio. Ningun microservicio deberia exponerse directamente a
 * clientes externos en un despliegue real; en este proyecto los puertos
 * individuales se mantienen publicados solo para poder inspeccionar cada
 * Swagger durante el desarrollo.
 */
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
