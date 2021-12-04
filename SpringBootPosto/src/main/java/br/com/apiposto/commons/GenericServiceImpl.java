package br.com.apiposto.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {



	@Override
	public T save(T entity) {
		try {
			return getDao().save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(ID id) {
		try {
			getDao().deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public T get(ID id) {
	try {	
		Optional<T> obj = getDao().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
	}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	@Override
	public List<T> getAll() {
		try {

			List<T> returnList = new ArrayList<>();
			getDao().findAll().forEach(obj -> returnList.add(obj));
			return returnList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public abstract CrudRepository<T, ID> getDao();

}
