package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.UsuarioService;
import br.com.apiposto.service.imp.GeolocalizacaoService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GeolocalizacaoService geolocalizacaoService;
	

	@GetMapping
	public ResponseEntity<List<Usuario>> obterTodos(@RequestParam (required = false) String nome) {
		return this.usuarioService.obterTodos(nome);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obterPorId (@PathVariable ("id") String id) {
		return this.usuarioService.obterPorId(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizarUsuario( @PathVariable ("id") String id, @RequestBody Usuario usuario){
		return this.usuarioService.atualizarUsuario(id, usuario);
	}
	
	@DeleteMapping
	public ResponseEntity<HttpStatus> deletarUsuario (@PathVariable ("id") String id) {
		return this.usuarioService.deletarUsuario(id);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario (@RequestBody Usuario usuario) {
		return this.usuarioService.criarUsuario(usuario, geolocalizacaoService);
	}
	
	
}
