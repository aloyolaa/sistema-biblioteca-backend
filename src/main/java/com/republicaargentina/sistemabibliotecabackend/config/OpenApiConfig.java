package com.republicaargentina.sistemabibliotecabackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Sistema Biblioteca API",
                version = "1.0",
                description = "Documentación y Endpoints para Sistema Biblioteca API"
        )
)
public class OpenApiConfig {
}
