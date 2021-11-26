package br.com.apiposto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.service.PostoService;

@Controller
public class PostoController {

	
	@Autowired
	private PostoService postoService;

	
	@PostMapping("/posto/salvar")
	public String salvar(@ModelAttribute Posto posto) {
			postoService.salvar(posto);
			return "redirect:/";
	}
	
	@GetMapping("/posto/listar")
	public String listar(Model model) {
		postoService.listar(model);
		return "posto/listar";
	}

	@GetMapping("/posto/pesquisarnome")
	public String pesquisarNome() {
		return "posto/pesquisarnome";
	}

	public String pesquisar(@RequestParam("nome") String nome, Model model) {
		postoService.pesquisar(nome, model);
		return "posto/pesquisarnome";
	}
	
	
	
	

}
