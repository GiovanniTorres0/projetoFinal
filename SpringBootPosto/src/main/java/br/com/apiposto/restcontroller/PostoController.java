package br.com.apiposto.restcontroller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.apiposto.dto.PostoDto;
import br.com.apiposto.form.PostoForm;
import br.com.apiposto.modelo.Posto;
import br.com.apiposto.repository.PostoRepository;
import br.com.apiposto.service.PostoService;
import br.com.apiposto.service.imp.GeolocalizacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/posto")
public class PostoController {

	@Autowired
	private PostoService postoService;

	@Autowired
	private PostoRepository postoRepository;

	@Autowired
	private GeolocalizacaoService geolocalizacaoService;

	@ApiOperation(value = "Busca varios postos", notes = "Busca varios postos de combustível", response = PostoController.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "/all")
	@Cacheable(value = "getAllPosto")
	public List<PostoDto> getAll() {
		List<Posto> postos = postoService.getAll();
		return PostoDto.converter(postos);
	}

	@ApiOperation(value = "Busca um Posto", notes = "Busca um posto de combustível pelo id", response = PostoController.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(value = "/find/{id}")
	@Cacheable(value = "findPosto")
	public Posto find(@PathVariable Long id) {
		return postoService.get(id);
	}

	@ApiOperation(value = "Cadastra um Posto", notes = "Cadastro de Posto de combustível", response = PostoController.class)
	@ApiResponses({ @ApiResponse(code = 201, message = "Created") })
	@PostMapping(value = "/save")
	@Caching(evict = { @CacheEvict("getAllPosto"), @CacheEvict(value = "findPosto", key = "#p0") })
	public ResponseEntity<PostoDto> save(@RequestBody @Valid PostoForm postoForm) {
		Posto posto = postoForm.converterPosto(postoForm, geolocalizacaoService, postoRepository);
		if (posto != null) {
			Posto obj = postoService.save(posto);
			return new ResponseEntity<PostoDto>(new PostoDto(obj), HttpStatus.CREATED);

		} else {
			posto = null;
			System.out.println("Posto inválido");
			return new ResponseEntity<PostoDto>(HttpStatus.BAD_REQUEST);

		}

	}

	@ApiOperation(value = "Deleta um Posto", notes = "Deleta um Posto de combustível", response = PostoController.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@DeleteMapping(value = "/delete/{id}")
	@Caching(evict = { @CacheEvict("getAllPosto"), @CacheEvict(value = "findPosto", key = "#p0") })
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
