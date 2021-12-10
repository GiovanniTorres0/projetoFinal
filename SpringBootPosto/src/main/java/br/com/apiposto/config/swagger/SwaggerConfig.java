package br.com.apiposto.config.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	   @Bean
	    SecurityContext securityContext() {
	        return SecurityContext.builder()
	                .securityReferences(defaultAuth())
	                .build();
	    }
	 
	    List<SecurityReference> defaultAuth() {
	    	  AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    	    authorizationScopes[0] = authorizationScope; 
	    	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	    }
	
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.apiposto"))
                .paths(PathSelectors.any())  
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API Posto de Combustível")
                .description("\"API de gelocalização de postos de combustível\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley")
                .contact(new Contact("ContatoTeste", "http://springframework.guru/about/", "teste@example.com"))
                .build();
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}