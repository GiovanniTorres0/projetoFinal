package br.com.apiposto;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

@SpringBootApplication
public class ApipostoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApipostoApplication.class, args);
		
		
		ViaCEPClient cliente =  new ViaCEPClient();
		try {
			ViaCEPEndereco endereco = cliente.getEndereco("82010340");
			System.out.println(endereco.getBairro());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}