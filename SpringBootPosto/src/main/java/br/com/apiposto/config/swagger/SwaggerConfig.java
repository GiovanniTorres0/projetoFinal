package br.com.apiposto.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage( "br.com.apiposto" ))             
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());

    }

	private ApiInfo apiInfo() {

		ApiInfo apiInfo = new ApiInfoBuilder().title("API de Postos de combustível")
				.description("Api de distancia entre usuário e postos de combustível").license("Apache License Version 2.0")
				.licenseUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley").termsOfServiceUrl("/service.html")
				.version("0.0.1")
				.build();
				
		return apiInfo;
	}

}