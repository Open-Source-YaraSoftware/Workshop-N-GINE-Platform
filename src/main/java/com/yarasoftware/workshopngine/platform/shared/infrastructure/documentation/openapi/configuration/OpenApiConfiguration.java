package com.yarasoftware.workshopngine.platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI workshopngineOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Workshop N-GINE API")
                        .description("API documentation for the Workshop N-GINE application, a platform for automotive workshop management.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .servers(List.of(
                        new Server().url("https://workshop-n-gine-platform-production.up.railway.app")
                ));
    }
}
