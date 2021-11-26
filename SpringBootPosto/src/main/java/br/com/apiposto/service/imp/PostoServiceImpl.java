package br.com.apiposto.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.repository.PostoRepository;
import br.com.apiposto.service.PostoService;

@Service
public class PostoServiceImpl implements PostoService {

	@Autowired
	private PostoRepository postoRepository;

	@Autowired
	private GeolocalizacaoService geolocalizacaoService;

	@Override
	public void salvar(@ModelAttribute Posto posto) {
		System.out.println("Posto para salvar: " + posto);
		try {
			List<Double> latElong = geolocalizacaoService.obterLateLong(posto.getUbicacion());
			posto.getUbicacion().setCordinates(latElong);
			postoRepository.salvar(posto);
		} catch (Exception e) {
			System.out.println("Endereco nao localizado");
			e.printStackTrace();
		}

	}

	@Override
	public void listar(Model model) {
		List<Posto> postos = postoRepository.obterTodosPostos();
		model.addAttribute("postos", postos);

	}

	@Override
	public void pesquisar(@RequestParam("nome") String nome, Model model) {
		List<Posto> postos = postoRepository.pesquisarPor(nome);

		model.addAttribute("postos", postos);

	}

}