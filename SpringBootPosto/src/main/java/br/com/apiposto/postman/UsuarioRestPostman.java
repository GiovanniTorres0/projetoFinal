package br.com.apiposto.postman;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import com.google.maps.errors.ApiException;

import br.com.apiposto.dto.UsuarioDto;
import br.com.apiposto.form.UsuarioForm;
import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.UsuarioService;
import br.com.apiposto.service.imp.GeolocalizacaoService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestPostman {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GeolocalizacaoService geolocalizacaoService;

	@GetMapping(value = "/all")
	@Cacheable(value = "getAllUsuario")
	public List<UsuarioDto> getAll() {
		List<Usuario> usuarios = usuarioService.getAll();
		return UsuarioDto.converter(usuarios);
	}

	@GetMapping(value = "/find/{id}")
	@Cacheable(value = "findUsuario")
	public Usuario find(@PathVariable Long id) {
		return usuarioService.get(id);
	}

	@PostMapping(value = "/save")
	@Caching(evict = {
			@CacheEvict ("getAllUsuario"),
			@CacheEvict(value = "findUsuario", key = "#p0")
	})	
	public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioForm usuarioForm) {

		
		
		Usuario usuario = usuarioForm.converter(usuarioForm);
		usuario.getUbicacion().setId((long) 1);

		ViaCEPClient cliente = new ViaCEPClient();
		try {
			ViaCEPEndereco endereco = cliente.getEndereco(usuario.getUbicacion().getCep());
			System.out.println(endereco.getLogradouro());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			List<Double> latElong = geolocalizacaoService.obterLatELong(usuario.getUbicacion());
			usuario.getUbicacion().setCoordinates(latElong);

		} catch (ApiException e) {
			System.out.println("API Exception");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Interrupted Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Indereco nao encontrado");
			e.printStackTrace();
		}

		String encode = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(encode);
	
		Usuario obj = usuarioService.save(usuario);
		return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	@Caching(evict = {
			@CacheEvict ("getAllUsuario"),
			@CacheEvict(value = "findUsuario", key = "#p0")
	})
	public ResponseEntity<Usuario> delete(@PathVariable Long id) {
		Usuario usuario = usuarioService.get(id);

		if (usuario != null) {
			usuarioService.delete(id);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

}
