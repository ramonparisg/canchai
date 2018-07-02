package com.eiffel.canchai.dao.interfaces;

import java.util.List;

import com.eiffel.canchai.model.ImageField;

public interface IImageFieldDao {

	void save(final ImageField entity);
	
	List<ImageField> findBySportCenter(int idSportCenter);
	
	ImageField findById(final int id);
}
