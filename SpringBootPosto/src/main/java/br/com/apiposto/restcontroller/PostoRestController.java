package br.com.apiposto.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.service.PostoService;


@RestController
@RequestMapping("/posto/api")
public class PostoRestController {

	@Autowired
	private PostoService postoServiceAPI;

	@GetMapping(value = "/all")
	public List<Posto> getAll() {
		return postoServiceAPI.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Posto find(@PathVariable Long id) {
		return postoServiceAPI.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Posto> save(@RequestBody Posto posto) {
		Posto obj = postoServiceAPI.save(posto);
		return new ResponseEntity<Posto>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Posto> delete(@PathVariable Long id) {
		Posto posto = postoServiceAPI.get(id);
		if (posto != null) {
			postoServiceAPI.delete(id);
		} else {
			return new ResponseEntity<Posto>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Posto>(posto, HttpStatus.OK);
	}

}
	
	

