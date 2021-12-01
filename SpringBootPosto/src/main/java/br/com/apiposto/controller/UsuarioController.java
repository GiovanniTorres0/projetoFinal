package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.UsuarioServiceApi;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioServiceApi usuarioServiceApi;

	@GetMapping("usuario/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/cadastrar";
	}
	
	@GetMapping("usuario/listar")
	public String listar(Model model) {
		List<Usuario> usuarios = usuarioServiceApi.getAll();
		model.addAttribute("usuarios",usuarios);
		return "usuario/listar";
		
	}
	
}
