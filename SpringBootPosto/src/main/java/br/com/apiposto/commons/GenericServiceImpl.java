package br.com.apiposto.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.apiposto.config.ErroDeValidacionHandler;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {

	private MethodArgumentNotValidException exception;
	private ErroDeValidacionHandler ev = new ErroDeValidacionHandler();

	@Override
	public T save(T entity) {
		try {
			return getDao().save(entity);
		} catch (Exception e) {
			ev.handler(exception);
			return null;
		}
	}

	@Override
	public void delete(ID id) {
		try {
			getDao().deleteById(id);
		} catch (Exception e) {
			ev.handler(exception);
		}

	}

	@Override
	public T get(ID id) {

		Optional<T> obj = getDao().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			ev.handler(exception);
			return null;
		}

	}

	@Override
	public List<T> getAll() {
		try {

			List<T> returnList = new ArrayList<>();
			getDao().findAll().forEach(obj -> returnList.add(obj));
			return returnList;

		} catch (Exception e) {
			ev.handler(exception);
			return null;
		}
	}

	public abstract CrudRepository<T, ID> getDao();

}
