package br.com.apiposto.service;

import org.springframework.ui.Model;

import br.com.apiposto.modelo.Usuario;

public interface UsuarioService {

	public String cadastrar(Model model);

	public String salvar(Usuario usuario);

	public String listar(Model model);

	public String visualizar(String id, Model model);

	public String pesquisarNome();

	public String pesquisar(String nome, Model model);

}
