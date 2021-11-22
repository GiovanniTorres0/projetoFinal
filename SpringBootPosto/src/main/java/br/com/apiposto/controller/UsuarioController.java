package br.com.apiposto.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> obterTodos() {
		return this.usuarioService.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Usuario obterPorId(@PathVariable String id) {
		return this.usuarioService.obterPorId(id);
	}
	
	@PostMapping
	public Usuario criar(@RequestBody Usuario usuario) {
		return this.usuarioService.criar(usuario);
	}
	
	
	
}
