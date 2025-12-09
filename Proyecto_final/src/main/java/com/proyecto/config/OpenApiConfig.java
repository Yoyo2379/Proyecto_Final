package com.proyecto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Sistema de Gestión de Tareas y Proyectos API")
                .version("1.0.0")
                .description("API REST para gestionar proyectos y tareas con filtros y dashboard")
                .contact(new Contact()
                    .name("Equipo de Desarrollo")
                    .email("contacto@proyecto.com")));
    }
}
