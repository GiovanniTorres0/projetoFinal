package br.com.apiposto.repository;

import org.junit.Assert;
import org.junit.Test;
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

	@Test
	public void buscarPostoPorNome() {
		String nomePosto = "cabure";
		Posto posto = postoRepository.findByNome(nomePosto);
		Assert.assertEquals(nomePosto, posto.getNome());
	}

	@Test
	public void naoDeveriaCarregarUmPostoNaoCadastrado() {
		String nomePosto = "Ipiranga";
		Posto posto = postoRepository.findByNome(nomePosto);
		Assert.assertNull(posto);
	}

}
