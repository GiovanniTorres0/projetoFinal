package br.com.apiposto.service;

import org.springframework.ui.Model;

import br.com.apiposto.modelo.Posto;

public interface PostoService {

	public String cadastrar(Model model);

	public String salvar(Posto posto);

	public String listar(Model model);

	public String visualizar(String id, Model model);

	public String pesquisarNome();

	public String pesquisar(String nome, Model model);

}
