package br.com.apiposto.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.UsuarioServiceApi;

@RestController
@RequestMapping("/usuario/api")

public class UsuarioRestController {

	@Autowired
	private UsuarioServiceApi usuarioServiceAPI;

	@GetMapping(value = "/all")
	public List<Usuario> getAll() {
		return usuarioServiceAPI.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Usuario find(@PathVariable Long id) {
		return usuarioServiceAPI.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		Usuario obj = usuarioServiceAPI.save(usuario);
		return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable Long id) {
		Usuario usuario = usuarioServiceAPI.get(id);
		if (usuario != null) {
			usuarioServiceAPI.delete(id);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	


}
