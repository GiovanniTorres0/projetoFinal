package br.com.apiposto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.service.PostoService;

@Controller
public class PostoController {

	@Autowired
	private PostoService postoService;

	@GetMapping("/posto/cadastrar")
	public ResponseEntity<String> cadastrar(Model model) {
		try {
			return new ResponseEntity<>(this.postoService.cadastrar(model), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/posto/listar")
	public ResponseEntity<String> listar(Model model) {
		try {
			return new ResponseEntity<>(this.postoService.listar(model), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/posto/visualizar/{id}")
	public ResponseEntity<String> visualizar(@PathVariable String id, Model model) {
		try {
			return new ResponseEntity<>(this.postoService.visualizar(id, model), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/posto/pesquisarnome")
	public ResponseEntity<String> pesquisarNome() {
		try {
			return new ResponseEntity<>(this.postoService.pesquisarNome(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/posto/pesquisar")
	public ResponseEntity<String> pesquisar(@RequestParam("nome") String nome, Model model) {
		try {
			return new ResponseEntity<>(this.postoService.pesquisar(nome, model), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/posto/salvar")
	public ResponseEntity<String> salvar(@ModelAttribute Posto posto) {
		try {
			return new ResponseEntity<>(this.postoService.salvar(posto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
