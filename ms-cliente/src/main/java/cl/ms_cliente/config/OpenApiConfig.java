package cl.ms_cliente.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI msClienteOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Smartfix - ms-cliente")
                .description("Authentication and customer management microservice")
                .version("1.0.0"))
            // Agrega el botón "Authorize" para probar endpoints protegidos con JWT
            .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
            .components(new Components()
                .addSecuritySchemes(SCHEME_NAME, new SecurityScheme()
                    .name(SCHEME_NAME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }
}
