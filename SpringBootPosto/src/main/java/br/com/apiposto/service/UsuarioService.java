package br.com.apiposto.service;

import java.util.List;

import br.com.apiposto.modelo.Usuario;


public interface UsuarioService {

	public List<Usuario> obterTodos();
	
	public Usuario obterPorId(String id);
	
	public Usuario criar (Usuario usuario);
	
	
}
