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
public class GeolocalizacaoController {

	@Autowired

	@GetMapping("/geolocalizacao/iniciarpesquisa")
	public String inicializarPesquisa() {
		return "geolocalizacao/pesquisar";
	}

}
