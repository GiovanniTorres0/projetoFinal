package br.com.apiposto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ApipostoApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApipostoApplication.class, args);

	}

}