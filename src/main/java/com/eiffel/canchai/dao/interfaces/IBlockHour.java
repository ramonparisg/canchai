package com.eiffel.canchai.dao.interfaces;

import java.util.List;

import com.eiffel.canchai.model.BlockHour;

public interface IBlockHour {

	List<BlockHour> findAll();
	
	BlockHour findById(final int id);
}
