package br.com.apiposto.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
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

	

	@Test
	public void buscarUsuarioPorEmail() {
		String nomeEmail = "teste@exemplo.com";
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
	
	
	
	
	
	

