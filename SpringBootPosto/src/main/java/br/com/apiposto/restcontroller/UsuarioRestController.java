package br.com.apiposto.restcontroller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import com.google.maps.errors.ApiException;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.UsuarioServiceApi;
import br.com.apiposto.service.imp.GeolocalizacaoService;

@RestController
@RequestMapping("/usuario/api")
public class UsuarioRestController {

	@Autowired
	private UsuarioServiceApi usuarioServiceAPI;

	@Autowired
	private GeolocalizacaoService geolocalizacaoService;

	@GetMapping(value = "/all")
	public List<Usuario> getAll() {
		return usuarioServiceAPI.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public Usuario find(@PathVariable Long id) {
		return usuarioServiceAPI.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Usuario> save(@ModelAttribute Usuario usuario) {
		

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
