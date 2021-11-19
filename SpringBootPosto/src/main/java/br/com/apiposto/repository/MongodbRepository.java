package br.com.apiposto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.apiposto.modelo.Usuario;
public interface MongodbRepository extends MongoRepository<Usuario, Long> {

	Usuario findByNome(String nome);
}


	
	
