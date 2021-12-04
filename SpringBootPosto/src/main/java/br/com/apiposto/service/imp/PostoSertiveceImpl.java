package br.com.apiposto.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.apiposto.commons.GenericServiceImpl;
import br.com.apiposto.modelo.Posto;
import br.com.apiposto.repository.PostoRepository;
import br.com.apiposto.service.PostoService;


@Service
public class PostoSertiveceImpl extends GenericServiceImpl<Posto, Long> implements PostoService {

	@Autowired
	private PostoRepository postoRepositoy;

	@Override
	public CrudRepository<Posto, Long> getDao() {
				
		return postoRepositoy;
	}

}