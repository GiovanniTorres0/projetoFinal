package br.com.apiposto.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.imp.GeolocalizacaoService;

public interface UsuarioService {

	public ResponseEntity<List<Usuario>> obterTodos(String nome);

	public ResponseEntity<Usuario> obterPorId(String id);

	public ResponseEntity<Usuario> atualizarUsuario(String id, Usuario usuario);

	public ResponseEntity<HttpStatus> deletarUsuario(String id);

	public ResponseEntity<Usuario> criarUsuario(Usuario usuario, GeolocalizacaoService geolocalizacaoService);

}
