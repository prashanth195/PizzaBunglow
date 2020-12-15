package com.mytectra.springboot.PizzaBunglow.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenAPIConfig {

	@Bean
    public OpenAPI api() { 
        OpenAPI api = new OpenAPI()                                   
          .components(new Components())
          .info(new Info().title("Pizza Bunglow Application API").description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
        
        return api;
    }
	
}
