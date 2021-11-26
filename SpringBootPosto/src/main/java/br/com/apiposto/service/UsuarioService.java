package br.com.apiposto.service;

import org.springframework.ui.Model;

import br.com.apiposto.modelo.Usuario;

public interface UsuarioService {

	public void cadastrar(Model model);

	public void salvar(Usuario usuario);

	public void listar(Model model);

	public void visualizar(String id, Model model);

	public void pesquisar(String nome, Model model);


}
