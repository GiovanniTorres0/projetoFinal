package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.repository.UsuarioRepository;

public class GeolocalizacionUsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/geolocalizacao/usuario/inciarpesquisa")
	public String iniciarPesquisa(Model model) {

		List<Usuario> usuarios = usuarioRepository.obterTodosUsuarios();
		model.addAttribute("usuarios", usuarios);

		return "/geolocalizacion/usuario/pesquisar";
	}

	@GetMapping("/geolocalizacao/posto/pesquisar")
	public String pesquisar(@RequestParam("postoId") String usuarioId, Model model) {

		Usuario usuario = usuarioRepository.obterUsuarioPor(usuarioId);
		List<Usuario> usuariosProximos = usuarioRepository.pesquisaPorGeolocalizacao(usuario);
		model.addAttribute("usuariosProximos", usuariosProximos);
		return "/geolocalizacion/busqueda";
	}
	
}
