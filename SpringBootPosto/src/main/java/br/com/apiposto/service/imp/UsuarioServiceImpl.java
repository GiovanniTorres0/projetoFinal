package br.com.apiposto.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.repository.UsuarioRepository;
import br.com.apiposto.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> obterTodos() {
		return this.usuarioRepository.findAll();
	}

	@Override
	public Usuario obterPorId(String id) {
		return this.usuarioRepository
				.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuario não existe"));
	}

	@Override
	public Usuario criar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

}
