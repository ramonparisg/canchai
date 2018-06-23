package com.eiffel.canchai.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable>  {
	
	void save(final T entity);
	
	List<T> findAll();
	
	void deleteById(final int id);
	
	void update(final T entity);
	
	T findById(final int id);	

}
