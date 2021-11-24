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
	public String cadastrar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/cadastrar";
	}

	@Override
	public String salvar(@ModelAttribute Usuario usuario) {
		System.out.println("Posto para salvar: " + usuario);
		try {
			List<Double> latElong = geolocalizacaoService.obterLateLong(usuario.getUbicacion());
			usuario.getUbicacion().setCoordenadas(latElong);
			usuarioRepository.salvar(usuario);
		} catch (Exception e) {
			System.out.println("Endereco nao localizado");
			e.printStackTrace();
		}

		return "redirect:/";
	}

	@Override
	public String listar(Model model) {
		List<Usuario> usuarios = usuarioRepository.obterTodosUsuarios();
		model.addAttribute("usuario", usuarios);
		return "usuario/listar";
	}

	@Override
	public String visualizar(@PathVariable String id, Model model) {

		Usuario usuario = usuarioRepository.obterUsuarioPor(id);

		model.addAttribute("usuario", usuario);

		return "usuario/visualizar";
	}

	@Override
	public String pesquisarNome() {
		return "usuario/pesquisarnome";
	}

	@Override
	public String pesquisar(@RequestParam("nome") String nome, Model model) {
		List<Usuario> usuarios = usuarioRepository.pesquisarPor(nome);

		model.addAttribute("usuarios", usuarios);

		return "usuario/pesquisarnome";
	}

}