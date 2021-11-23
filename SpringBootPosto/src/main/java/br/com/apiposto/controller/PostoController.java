package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.service.PostoService;

@RestController
@RequestMapping("/Postos")
public class PostoController {

	@Autowired
	private PostoService postoService;

	@GetMapping
	public ResponseEntity<List<Posto>> obterTodos(@RequestParam (required = false) String nome) {
		return this.postoService.obterTodos(nome);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Posto> obterPorId (@PathVariable ("id") String id) {
		return this.postoService.obterPorId(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Posto> atualizaPosto( @PathVariable ("id") String id, @RequestBody Posto posto){
		return this.postoService.atualizarPosto(id, posto);
	}
	
	@DeleteMapping
	public ResponseEntity<HttpStatus> deletarPosto (@PathVariable ("id") String id) {
		return this.postoService.deletarPosto(id);
	}
	
	@PostMapping
	public ResponseEntity<Posto> criarPosto (@RequestBody Posto posto) {
		return this.postoService.criarPosto(posto);
	}

}
