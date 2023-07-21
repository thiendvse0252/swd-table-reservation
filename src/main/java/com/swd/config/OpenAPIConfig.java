package com.swd.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@OpenAPIDefinition(security = {@SecurityRequirement(name = "bearer-key")})

public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        // Local server
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080/");
        localServer.setDescription("Local server");
        Server newServer = new Server();
        newServer.setUrl("https://api.swd.nolamedia.tech/");
        newServer.setDescription("Production server");
        return new OpenAPI()
                .servers(Arrays.asList(localServer, newServer))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
