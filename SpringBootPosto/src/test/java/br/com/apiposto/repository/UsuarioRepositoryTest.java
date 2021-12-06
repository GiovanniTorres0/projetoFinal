package br.com.apiposto.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.apiposto.modelo.Usuario;

@DataMongoTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	Usuario pedro = new Usuario();

	@BeforeEach
	public void inicializar() {
		pedro.setId((long) 1533);
		pedro.setNome("Pedro Alvarez");
		pedro.setSenha("123456789");
		pedro.setEmail("teste@example.com");
		pedro.setUbicacion(null);
	}

	@Test
	public void UsuarioNaoEstaNulo() {
		Assert.assertNotNull(pedro);
	}

	@Test
	public void buscarUsuarioPorEmail() {
		String nomeEmail = "teste@example.com";
		Optional<Usuario> usuario = usuarioRepository.findByEmail(nomeEmail);
		String nomeCompara = usuario.toString();
		Assert.assertEquals(nomeEmail, nomeCompara);

	}

	@Test
	public void naoDeveriaCarregarUmUsuarioNaoCadastrado() {
		
		String nomeUsuario = "Valdinei";
		Usuario usuario = usuarioRepository.findByNome(nomeUsuario);
		Assert.assertNull(usuario);
	
	
	}
	
	
	
}
	
	
	
	
	
	

