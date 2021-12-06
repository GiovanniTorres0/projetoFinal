package br.com.apiposto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.apiposto.modelo.Ubicacion;

public interface UbicacionRepository extends MongoRepository<Ubicacion, Long> {

	Ubicacion findByCep(String cep);
}
