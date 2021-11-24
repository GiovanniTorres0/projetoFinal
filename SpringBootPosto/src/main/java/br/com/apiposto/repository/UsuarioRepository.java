package br.com.apiposto.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.apiposto.modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	List<Usuario> findByNomeContaining(String nome);
	List<Usuario> findByIdContaining(String id);


}
