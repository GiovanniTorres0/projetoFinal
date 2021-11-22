package br.com.apiposto.service;

import java.util.List;

import br.com.apiposto.modelo.Posto;


public interface PostoService {

	public List<Posto> obterTodos();
	
	public Posto obterPorId(String id);
	
	public Posto criar (Posto posto);
	
	
}
