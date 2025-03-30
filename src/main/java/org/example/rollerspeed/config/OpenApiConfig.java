package org.example.rollerspeed.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customerOpenAPI(){
        return new OpenAPI()
        .info(new Info().title("API de Roller Speed")
        .version("1.0")
        .description("Documentación de la API de Roller Speed"));

    }


}
