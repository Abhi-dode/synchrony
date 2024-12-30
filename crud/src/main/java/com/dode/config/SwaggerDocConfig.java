package com.dode.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerDocConfig {
	@Bean
	public GroupedOpenApi createDocket() {
		return GroupedOpenApi.builder().group("EmployeeAPI").pathsToMatch("/api/employee/**").build();
	}


	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("EmployeeAPI").description("Give info of Employee").version("1.14Relese")
				.termsOfService("https://google.com")
				.contact(new Contact().name("Abhijeet").email("dodeabhi@gmail.com").url("https://google.com")));
	}
}
