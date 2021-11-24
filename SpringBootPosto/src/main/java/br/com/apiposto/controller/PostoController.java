package br.com.apiposto.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String cadastrar(Model model) {
		return this.postoService.cadastrar(model);
	}

	@GetMapping("/posto/listar")
	public String listar(Model model) {
		return this.postoService.listar(model);
	}

	@GetMapping("/posto/visualizar/{id}")
	public String visualizar (@PathVariable String id, Model model) {
		return this.postoService.visualizar(id, model);
	}
	
	@GetMapping ("/posto/pesquisarnome")
	public String pesquisarNome() {
		return this.postoService.pesquisarNome();
	}
	
	@GetMapping("/posto/pesquisar")
	public String pesquisar(@RequestParam("nome") String nome, Model model){
		return this.postoService.pesquisar(nome, model);
	}
	
	@PostMapping("/posto/cadastrar")
	public String salvar(@ModelAttribute Posto posto) {
		return this.postoService.salvar(posto);
	}
	
}



