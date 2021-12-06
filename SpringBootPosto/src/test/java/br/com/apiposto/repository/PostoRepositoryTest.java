package br.com.apiposto.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.apiposto.modelo.Posto;
@DataMongoTest
@RunWith(SpringRunner.class)
public class PostoRepositoryTest {

	@Autowired
	PostoRepository postoRepository;
	
	Posto petobras = new Posto();
	
	@BeforeEach
	public void inicializar() {
		petobras.setId((long)1514);
		petobras.setNome("Petobras");
	}
	
	@Test
	public void postoNaoEstaNulo() {
		Assert.assertNotNull(petobras);
	}
	
	@Test
	public void buscarPostoPorNome() {
		String nomePosto = "Petobras";
		Posto posto = postoRepository.findByNome(nomePosto);
		Assert.assertEquals(nomePosto, posto);
	}
	
	@Test
	public void naoDeveriaCarregarUmPostoNaoCadastrado() {
		String nomePosto = "Ipiranga";
		Posto posto = postoRepository.findByNome(nomePosto);
		Assert.assertNull(posto);
	}
	
}
