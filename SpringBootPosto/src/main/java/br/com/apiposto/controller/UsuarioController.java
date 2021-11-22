package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	
}
