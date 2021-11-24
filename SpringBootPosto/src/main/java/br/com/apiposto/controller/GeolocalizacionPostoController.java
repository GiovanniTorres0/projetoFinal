package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.repository.PostoRepository;

@Controller
public class GeolocalizacionPostoController {

	@Autowired
	private PostoRepository postoRepository;

	@GetMapping("/geolocalizacao/posto/inciarpesquisa")
	public String iniciarPesquisa(Model model) {

		List<Posto> postos = postoRepository.obterTodosPostos();
		model.addAttribute("postos", postos);

		return "geolocalizacion/posto/pesquisar";
	}

	@GetMapping("/geolocalizacao/posto/pesquisar")
	public String pesquisar(@RequestParam("postoId") String postoId, Model model) {

		Posto posto = postoRepository.obterPostoPor(postoId);
		List<Posto> postosProximos = postoRepository.pesquisaPorGeolocalizacao(posto);
		model.addAttribute("postosProximos", postosProximos);
		return "geolocalizacion/posto/pesquisar";
	}
}
