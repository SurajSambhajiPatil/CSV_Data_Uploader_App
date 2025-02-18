package com.comsense.csvupload.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI restApiDoc() {
        return new OpenAPI()
                .info(new Info()
                        .title("CSV Data Uploader API")
                        .description("Microservice for uploading and processing CSV files")
                        .version("1.0"));
    }
}
