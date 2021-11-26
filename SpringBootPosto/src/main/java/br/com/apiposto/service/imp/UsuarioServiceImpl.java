package br.com.apiposto.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.repository.UsuarioRepository;
import br.com.apiposto.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GeolocalizacaoService geolocalizacaoService;

	@Override
	public void cadastrar(Model model) {
		model.addAttribute("usuario", new Usuario());
	}

	@Override
	public void salvar(@ModelAttribute Usuario usuario) {
		System.out.println("Usuario para salvar: " + usuario);
		try {
			List<Double> latElong = geolocalizacaoService.obterLateLong(usuario.getUbicacion());
			usuario.getUbicacion().setCordinates(latElong);
			usuarioRepository.salvar(usuario);
		} catch (Exception e) {
			System.out.println("Endereco nao localizado");
			e.printStackTrace();
		}

	}

	@Override
	public void listar(Model model) {
		List<Usuario> usuarios = usuarioRepository.obterTodosUsuarios();
		model.addAttribute("usuario", usuarios);
	}

	@Override
	public void visualizar(@PathVariable String id, Model model) {

		Usuario usuario = usuarioRepository.obterUsuarioPor(id);

		model.addAttribute("usuario", usuario);

	}

	@Override
	public void pesquisar(@RequestParam("nome") String nome, Model model) {
		List<Usuario> usuarios = usuarioRepository.pesquisarPor(nome);

		model.addAttribute("usuarios", usuarios);

	}

}