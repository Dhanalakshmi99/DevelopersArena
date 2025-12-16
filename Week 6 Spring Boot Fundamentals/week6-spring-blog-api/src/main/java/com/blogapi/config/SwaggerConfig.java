package com.blogapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI blogApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog Management API")
                        .description("RESTful API for managing blog posts, categories, and comments")
                        .version("1.0"));
    }
}

