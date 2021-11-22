package br.com.apiposto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.apiposto.modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

	
}
