package br.com.apiposto.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.apiposto.modelo.Posto;


public interface PostoService {

	
	public ResponseEntity<List<Posto>> obterTodos(String nome);

	public ResponseEntity<Posto> obterPorId(String id);

	public ResponseEntity<Posto> atualizarPosto(String id, Posto posto);

	public ResponseEntity<HttpStatus> deletarPosto(String id);

	public ResponseEntity<Posto> criarPosto(Posto posto);
	

}
