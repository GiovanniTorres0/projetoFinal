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

	@GetMapping("/usuario/cadastrar")
	public String cadastrar(Model model) {
		return this.usuarioService.cadastrar(model);
	}

	@GetMapping("/usuario/listar")
	public String listar(Model model) {
		return this.usuarioService.listar(model);
	}

	@GetMapping("/usuario/visualizar/{id}")
	public String visualizar(@PathVariable String id, Model model) {
		return this.usuarioService.visualizar(id, model);
	}

	@GetMapping("/usuario/pesquisarnome")
	public String pesquisarNome() {
		return this.usuarioService.pesquisarNome();
	}

	@GetMapping("/usuario/pesquisar")
	public String pesquisar(@RequestParam("nome") String nome, Model model) {
		return this.usuarioService.pesquisar(nome, model);
	}

	@PostMapping("/usuario/cadastrar")
	public String salvar(@ModelAttribute Usuario usuario) {
		return this.usuarioService.salvar(usuario);
	}

}
