package br.com.apiposto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.apiposto.modelo.Posto;

public interface PostoRepository extends MongoRepository<Posto, Long> {

}
