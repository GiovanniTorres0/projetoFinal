package br.com.apiposto.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.repository.PostoRepository;
import br.com.apiposto.service.PostoService;

@Service
public class PostoServiceImpl implements PostoService {

	@Autowired
	private PostoRepository postoRepository;

	@Override
	public ResponseEntity<List<Posto>> obterTodos(@RequestParam(required = false) String nome) {
		try {
			List<Posto> postos = new ArrayList<Posto>();

			if (nome == null)
				postoRepository.findAll().forEach(postos::add);
			else
				postoRepository.findByNomeContaining(nome).forEach(postos::add);

			if (postos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}

			return new ResponseEntity<>(postos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Posto> obterPorId(@PathVariable("id") String id) {
		Optional<Posto> Optional = postoRepository.findById(id);

		if (Optional.isPresent()) {
			return new ResponseEntity<>(Optional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Posto> atualizarPosto(@PathVariable("id") String id, @RequestBody Posto posto) {
		Optional<Posto> Optional = postoRepository.findById(id);

		if (Optional.isPresent()) {
			Posto _posto = Optional.get();
			_posto.setNome(posto.getNome());
			_posto.setUbicacion(posto.getUbicacion());
			return new ResponseEntity<>(postoRepository.save(_posto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<HttpStatus> deletarPosto(@PathVariable("id") String id) {
		try {
			postoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Posto> criarPosto(@RequestBody Posto posto) {
		try {
			Posto _posto = postoRepository.save(new Posto(posto.getNome(), posto.getUbicacion()));
			return new ResponseEntity<>(_posto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
