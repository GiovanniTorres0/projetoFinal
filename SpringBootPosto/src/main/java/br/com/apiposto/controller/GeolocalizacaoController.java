//package br.com.apiposto.controller;
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import br.com.apiposto.modelo.Posto;
//import br.com.apiposto.repository.PostoRepository;
//
//@Controller
//public class GeolocalizacaoController {
//	
//	@Autowired
//	private PostoRepository postoRepository;
//	
//	@GetMapping("/geolocalizacao/iniciarpesquisa")
//	public String inicializarPesquisa(Model model){
//		List<Posto> posto = postoRepository.obterTodosPostos();
//		
//		model.addAttribute("posto", posto);
//		
//		return "geolocalizacao/pesquisar";
//	}
//	
//	@GetMapping("/geolocalizacao/pesquisar")
//	public String pesquisar(@RequestParam("postoId") String alunoId, Model model){
//		
//		Posto posto = postoRepository.obterPostoPor(alunoId);
//		
//		List<Posto> postoProximos = postoRepository.pesquisaPorGeolocalizacao(posto);
//		
//		model.addAttribute("postoProximos", postoProximos);
//		
//		return "geolocalizacao/pesquisar";
//	}
//
//}
