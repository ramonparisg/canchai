package com.eiffel.canchai.service.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T extends Serializable>  {
	
	void save(final T entity);
	
	List<T> findAll();
	
	void deleteById(final int id);
	
	void update(final T entity);
	
	T findById(final int id);	

}
