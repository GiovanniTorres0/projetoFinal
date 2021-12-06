package br.com.apiposto.postman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.service.PostoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/posto/postman")
public class PostoRestPostman {

	@Autowired
	private PostoService postoService;

	
	@ApiOperation(value = "Busca varios postos", notes = "Busca varios postos de combustível", response = PostoRestPostman.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "/all")
	@Cacheable(value = "getAllPosto")
	public List<Posto> getAll() {
		return postoService.getAll();
	}

	@ApiOperation(value = "Busca um Posto", notes = "Busca um posto de combustível pelo id", response = PostoRestPostman.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "/find/{id}")
	@Cacheable(value = "findPosto")
	public Posto find(@PathVariable Long id) {
		return postoService.get(id);
	}
	
	@ApiOperation(value = "Cadastra um Posto", notes = "Cadastro de Posto de combustível", response = PostoRestPostman.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "Created") })
	@PostMapping(value = "/save")
	@Caching(evict = {
			@CacheEvict ("getAllPosto"),
			@CacheEvict(value = "findPosto", key = "#p0")
	})	
	public ResponseEntity<Posto> save(@RequestBody Posto posto) {
		Posto obj = postoService.save(posto);
		return new ResponseEntity<Posto>(obj, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Deleta um Posto", notes = "Deleta um Posto de combustível", response = PostoRestPostman.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })	
	@DeleteMapping(value = "/delete/{id}")
	@Caching(evict = {
			@CacheEvict ("getAllPosto"),
			@CacheEvict(value = "findPosto", key = "#p0")
	})	
	public ResponseEntity<Posto> delete(@PathVariable Long id) {
		Posto posto = postoService.get(id);
		if (posto != null) {
			postoService.delete(id);
		} else {
			return new ResponseEntity<Posto>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Posto>(posto, HttpStatus.OK);
	}

	

}
