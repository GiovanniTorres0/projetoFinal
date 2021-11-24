package br.com.apiposto.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.repository.UsuarioRepository;
import br.com.apiposto.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public ResponseEntity<List<Usuario>> obterTodos(@RequestParam(required = false) String nome) {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();

			if (nome == null)
				usuarioRepository.findAll().forEach(usuarios::add);
			else
				usuarioRepository.findByNomeContaining(nome).forEach(usuarios::add);

			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}

			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Usuario> obterPorId(@PathVariable("id") String id) {
		Optional<Usuario> Optional = usuarioRepository.findById(id);

		if (Optional.isPresent()) {
			return new ResponseEntity<>(Optional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("id") String id, @RequestBody Usuario usuario) {
		Optional<Usuario> Optional = usuarioRepository.findById(id);

		if (Optional.isPresent()) {
			Usuario _usuario = Optional.get();
			_usuario.setNome(usuario.getNome());
			_usuario.setUbicacion(usuario.getUbicacion());
			return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<HttpStatus> deletarUsuario(@PathVariable("id") String id) {
		try {
			usuarioRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario,
			GeolocalizacaoService geolocalizacaoService) {
		try {
			List<Double> latElong = geolocalizacaoService.obterLateLong(usuario.getUbicacion());
			usuario.getUbicacion().setCoordenadas(latElong);
			Usuario _usuario = usuarioRepository.save(new Usuario(usuario.getNome(), usuario.getUbicacion()));
			return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
