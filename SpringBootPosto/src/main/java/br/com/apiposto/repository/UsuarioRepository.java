package br.com.apiposto.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.google.common.base.Optional;

import br.com.apiposto.modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

	@Query("db.address.find ({\r\n"
			+ "  location: {\r\n"
			+ "     $near: {\r\n"
			+ "       $geometry: {\r\n"
			+ "          type: \"Point\" ,\r\n"
			+ "          coordinates: [ -72.7738706,41.6332836 ]\r\n"
			+ "       },\r\n"
			+ "       $maxDistance: 4,\r\n"
			+ "       $minDistance: 0\r\n"
			+ "     }\r\n"
			+ "   }\r\n"
			+ "})")
	List<Usuario> pesquisaPorGeolocalizacao(Usuario usuario);
	
	Optional<Usuario> findByEmail(String email);

	
	

}
