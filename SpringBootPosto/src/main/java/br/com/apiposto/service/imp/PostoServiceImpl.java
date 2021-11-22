package br.com.apiposto.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.repository.PostoRepository;
import br.com.apiposto.service.PostoService;

@Service
public class PostoServiceImpl implements PostoService {

	@Autowired
	private PostoRepository postoRepository;

	@Override
	public List<Posto> obterTodos() {
		return this.postoRepository.findAll();

	}

	@Override
	public Posto obterPorId(String id) {
		return this.postoRepository
				.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Posto não existe"));
	}

	@Override
	public Posto criar(Posto posto) {
		return this.postoRepository.save(posto);
	}

}
