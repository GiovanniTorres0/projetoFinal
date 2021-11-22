package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.service.PostoService;

@RestController
@RequestMapping("/Postos")
public class PostoController {

	@Autowired
	private PostoService postoService;

	@GetMapping
	public List<Posto> obterTodos() {
		return this.postoService.obterTodos();
	}

	@GetMapping("/{id}")
	public Posto obterPorId(@PathVariable String id) {
		return this.postoService.obterPorId(id);
	}

	@PostMapping
	public Posto criar(@RequestBody Posto posto) {
		return this.postoService.criar(posto);
	}

}
