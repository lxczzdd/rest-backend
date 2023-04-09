package com.backend.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Social media app",
                description = "RESTful application", version = "1.0.0",
                contact = @Contact(
                        email = "vasilyev.yaroslavv@gmail.com",
                        url = "tg: @zxcr123"
                )
        )
)
public class SwaggerConfig {

}
