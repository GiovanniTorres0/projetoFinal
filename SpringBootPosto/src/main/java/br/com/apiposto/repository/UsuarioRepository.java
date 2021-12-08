package br.com.apiposto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.apiposto.modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findById(Long idUsuario);

	Usuario findByNome(String nome);

	
	
	
	
	


	
	

}
