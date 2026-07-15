package cl.bff_web.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI bffOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Smartfix - BFF Web")
                .description("Backend For Frontend: unico punto de entrada de negocio del sistema. " +
                        "Orquesta ms-cliente, ms-reparacion, ms-notificacion, ms-cotizacion, ms-garantia, " +
                        "ms-conversor, ms-diagnostico, ms-inventario, ms-factura, ms-encuesta y ms-auditoria. " +
                        "Todo el trafico externo llega primero al API Gateway, que reenvia aqui.")
                .version("2.0.0"))
            // Servers explicitos: evita que springdoc autodetecte el hostname
            // interno de Docker ("bff") cuando el spec se pide a traves del
            // Gateway. Sin esto, "Try it out" arma curls a http://bff:8083
            // que el navegador (fuera de la red de Docker) no puede resolver.
            .addServersItem(new Server().url("http://localhost:8083").description("BFF directo"))
            .addServersItem(new Server().url("http://localhost:8080").description("A traves del Gateway"))
            .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
            .components(new Components()
                .addSecuritySchemes(SCHEME_NAME, new SecurityScheme()
                    .name(SCHEME_NAME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }
}
