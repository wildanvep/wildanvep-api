package com.java.wildanvep.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

	private static final String SECURITY_SCHEME_NAME = "Bearer oAuth Token";

	private static final String OPEN_API_TITLE = "WildanVEP";
	private static final String OPEN_API_VERSION = "v1.0.0";
	private static final String OPEN_API_DESCRIPTION = "My API Portofolio";

	/**
	 * Open API Configuration Bean
	 *
	 * @param title
	 * @param version
	 * @param description
	 * @return
	 */
	@Bean
	public OpenAPI openApiConfiguration(@Value(OPEN_API_TITLE) final String title,
			@Value(OPEN_API_VERSION) final String version, @Value(OPEN_API_DESCRIPTION) final String description) {
		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
				.components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME,
						new SecurityScheme().name(SECURITY_SCHEME_NAME).type(SecurityScheme.Type.HTTP).scheme("bearer")
								.bearerFormat("JWT")))
				.info(new Info().title(title).version(version).description(description)
						.termsOfService("Terms of service").license(getLicense()).contact(getContact()));
	}

	/**
	 * Contact details for the developer(s)
	 *
	 * @return
	 */
	private Contact getContact() {
		Contact contact = new Contact();
		contact.setEmail("wildanviado05@gmail.com");
		contact.setName("Wildan Viado Elvana Putra");
		contact.setUrl("https://www.linkedin.com/wildanvep");
		contact.setExtensions(Collections.emptyMap());
		return contact;
	}

	/**
	 * License creation
	 *
	 * @return
	 */
	private License getLicense() {
		License license = new License();
		license.setName("Apache License, Version 2.0");
		license.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
		license.setExtensions(Collections.emptyMap());
		return license;
	}

}
