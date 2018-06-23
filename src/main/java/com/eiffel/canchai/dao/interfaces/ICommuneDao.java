package com.eiffel.canchai.dao.interfaces;

import java.util.List;

import com.eiffel.canchai.model.Commune;

public interface ICommuneDao{
	List<Commune> findAll();
}
