package cl.ms_encuesta.config;

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
    public OpenAPI msEncuestaOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Smartfix - ms-encuesta")
                .description("Encuestas de satisfaccion posteriores a una reparacion")
                .version("1.0.0"))
            .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
            .components(new Components()
                .addSecuritySchemes(SCHEME_NAME, new SecurityScheme()
                    .name(SCHEME_NAME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }
}
