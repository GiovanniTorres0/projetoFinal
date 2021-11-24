package br.com.apiposto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.repository.PostoRepository;

@RestController
@RequestMapping("/geolocalizacion")
public class GeolocalizacionController {

	@Autowired
	private PostoRepository postoRepository;

	@GetMapping("/iniciarbusqueda")
	public String iniciarBusqueda(Model model) {

		List<Posto> postos = postoRepository.findAll();
		model.addAttribute("postos", postos);

		return "/geolocalizacion/busqueda";
	}

	@GetMapping("/busqueda")
	public String busqueda(@RequestParam("puestoId") String puestoId, Model model) {

		Posto posto = postoRepository.findByIdContaining(puestoId);
		List<Posto> postosProximos = postoRepository.busquedaPorGeolocalizacion(posto);
		model.addAttribute("postosProximos", postosProximos);
		return "/geolocalizacion/busqueda";
	}
}
