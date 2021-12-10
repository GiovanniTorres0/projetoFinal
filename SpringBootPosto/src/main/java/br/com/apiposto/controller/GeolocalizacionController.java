package br.com.apiposto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.modelo.Ubicacion;
import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.repository.PostoRepository;
import br.com.apiposto.repository.UsuarioRepository;
import br.com.apiposto.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
public class GeolocalizacionController {

	@Autowired
	private UsuarioService usuarioServiceApi;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostoRepository postoRepository;

	@ApiOperation(value = "Inicia pesquisa de usuarios", notes = "Busca usuarios cadastrados no banco", response = GeolocalizacionController.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping("/")
	public String inicializarPesquisa(Model model) {
		List<Usuario> usuarios = usuarioServiceApi.getAll();
		
		
		model.addAttribute("usuarios", usuarios);

		return "index";
	}

	@GetMapping("/index")
	public String pesquisar(@RequestParam("usuarioId") String usuarioId, Model model) {

		long id = Long.parseLong(usuarioId);
		System.out.println("ID de busqueda" + id);
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			System.out.println("ID is present");
			List<Posto> totalDePostos = postoRepository.findAll();
			
		    Posto postoUsuario= new Posto();
		    Ubicacion ubicacionUsuario = usuario.get().getUbicacion();
		    postoUsuario.setUbicacion(ubicacionUsuario);
		    postoUsuario.getUbicacion().setCoordinates(usuario.get().getUbicacion().getCoordinates());
		    postoUsuario.setNome(usuario.get().getNome());
			for (Posto posto : totalDePostos) {
				System.out.println(posto.getUbicacion().getCoordinates());
			}
			
			List<Posto> postosProximos = new ArrayList<Posto>();
			postosProximos.add(postoUsuario);
			postosProximos.addAll(totalDePostos);
			model.addAttribute("usuariosProximos", postosProximos);


		}

		return "index";
	}

}
