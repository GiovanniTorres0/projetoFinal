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

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/usuario/cadastrar")
	public ResponseEntity<String> cadastrar(Model model) {
		try {
			return new ResponseEntity<>(this.usuarioService.cadastrar(model), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/usuario/listar")
	public ResponseEntity<String> listar(Model model) {
		try {
			return new ResponseEntity<>(this.usuarioService.listar(model), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/usuario/visualizar/{id}")
	public ResponseEntity<String> visualizar(@PathVariable String id, Model model) {
		try {
			return new ResponseEntity<>(this.usuarioService.visualizar(id, model), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/usuario/pesquisarnome")
	public ResponseEntity<String> pesquisarNome() {
		try {
			return new ResponseEntity<>(this.usuarioService.pesquisarNome(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/usuario/pesquisar")
	public ResponseEntity<String> pesquisar(@RequestParam("nome") String nome, Model model) {
		try {
			return new ResponseEntity<>(this.usuarioService.pesquisar(nome, model), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/usuario/salvar")
	public ResponseEntity<String> salvar(@ModelAttribute Usuario usuario) {
		try {
			return new ResponseEntity<>(this.usuarioService.salvar(usuario), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
