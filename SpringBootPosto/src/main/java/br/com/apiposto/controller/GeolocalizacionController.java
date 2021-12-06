package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
public class GeolocalizacionController {

	@Autowired
	private UsuarioService usuarioServiceApi;
	
	@ApiOperation(value = "Inicia pesquisa de usuarios", notes = "Busca usuarios cadastrados no banco", response = GeolocalizacionController.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })	
	@GetMapping("/geolocalizacao/iniciarpesquisa")
	public String inicializarPesquisa(Model model) {
		List<Usuario> usuarios = usuarioServiceApi.getAll();
				
		model.addAttribute("titulo", "TESSS");
		model.addAttribute("alunos", usuarios);

		return "geolocalizacao/pesquisar";
	}
//
//	@GetMapping("/geolocalizacao/pesquisar")
//	public String pesquisar(@RequestParam("alunoId") String usuarioId, Model model) {
//
//		long id = Long.parseLong(usuarioId);
//		Optional<Usuario> alunosProximos = usuarioRepository.findById(id);
//		if (alunosProximos.isPresent()) {
//			return "Error";
//
//		}
//		model.addAttribute("alunosProximos", alunosProximos);
//
//		return "geolocalizacao/pesquisar";
//	}

}
