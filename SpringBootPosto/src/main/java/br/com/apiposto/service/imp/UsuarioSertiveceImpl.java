package br.com.apiposto.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.apiposto.commons.GenericServiceImpl;
import br.com.apiposto.modelo.Usuario;
import br.com.apiposto.repository.UsuarioRepository;
import br.com.apiposto.service.UsuarioServiceApi;


@Service
public class UsuarioSertiveceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioServiceApi {

	@Autowired
	private UsuarioRepository usuarioRepositoy;

	@Override
	public CrudRepository<Usuario, Long> getDao() {

		return usuarioRepositoy;
	}

}