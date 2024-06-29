package com.petitjy.threadit.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	private String JWT = "JWT";

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().title("Treadit API");
		Components components = new Components().addSecuritySchemes(JWT, new SecurityScheme()
			.name(JWT)
			.type(SecurityScheme.Type.HTTP)
			.scheme("Bearer")
			.bearerFormat(JWT)
		);

		return new OpenAPI().info(info).components(components);
	}
}
