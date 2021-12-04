package br.com.apiposto.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

public class SwaggerConfig {

	@Configuration
	@EnableSwagger2
	public class SwaggerConfiguration {

		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("br.com.apiposto")).build().apiInfo(getApiInfo());
		}

		private ApiInfo getApiInfo() {

			return new ApiInfoBuilder().title("Posto API").description("Management of Posto").license("")
					.licenseUrl("http://google.com").termsOfServiceUrl("").version("1.0")
					.contact(new Contact("Sntaigo Martin Pereira", "", "santiagomartin614@gmail.com")).build();
		}
	}
}