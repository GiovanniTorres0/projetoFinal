package br.com.apiposto.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	//Cadastra usuario sem localização.
	@GetMapping("/usuario/cadastrar")
	public String cadastrar(Model model) {
		usuarioService.cadastrar(model);
		return "usuario/cadastrar";
	}

	@GetMapping("/usuario/listar")
	public String listar(Model model) {
		usuarioService.listar(model);
		return "usuario/listar";
	}
	
	@GetMapping("/usuario/visualizar/{id}")
	public String visualizarPorId(@PathVariable String id, Model model) {
		usuarioService.visualizar(id, model);
		return "usuario/visualizar";
	}
		

	@GetMapping("/usuario/pesquisarnome")
	public String pesquisarNome() {
		return "usuario/pesquisarnome";
	}

		
	@GetMapping("/usuario/pesquisar")
	public String pesquisar(@RequestParam("nome") String nome, Model model) {
		usuarioService.visualizar(nome, model);
		return "usuario/pesquisarnome";
	}

	@PostMapping("/usuario/salvar")
	public String salvar(@ModelAttribute Usuario usuario) {
		usuarioService.salvar(usuario);
		return "redirect:/";
	}

}
