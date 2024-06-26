package org.tan.booknetwork.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Tanmay",
                        email = "tanmaydhanvijay0586@gmail.com"
                ),
                description = "OpenApi documentation for Spring security",
                title = "OpenApi specification - Tanmay",
                version = "1.0.0",
                license = @License(
                        name = "License Name"
                )
        ),
        servers = {
                @Server(
                        description = "local Environment",
                        url = "http://localhost:8080/api/v1"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuthentication"
                )
        }
)
@SecurityScheme(
        //The name should be same as the same provided in the SecurityRequirement.
        name = "bearerAuthentication",
        description = "JWT Authentication description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
