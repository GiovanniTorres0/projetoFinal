package br.com.apiposto.service;

import org.springframework.ui.Model;

import br.com.apiposto.modelo.Posto;

public interface PostoService {

	public void salvar(Posto posto);

	public void listar(Model model);

	public void pesquisar(String nome, Model model);

	


}
