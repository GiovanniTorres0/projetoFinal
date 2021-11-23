package br.com.apiposto.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.apiposto.modelo.Posto;

public interface PostoRepository extends MongoRepository<Posto, String> {

	List<Posto> findByNomeContaining(String nome);

}
