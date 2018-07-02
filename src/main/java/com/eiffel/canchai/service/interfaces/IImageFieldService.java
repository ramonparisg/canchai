package com.eiffel.canchai.service.interfaces;

import java.util.List;

import com.eiffel.canchai.model.ImageField;

public interface IImageFieldService{

	void save(final ImageField entity);
	
	List<ImageField> findBySportCenter(int idSportCenter);
	
	ImageField findById(final int id);
}
