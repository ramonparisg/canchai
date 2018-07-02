package com.eiffel.canchai.service.interfaces;

import java.util.List;

import com.eiffel.canchai.model.BlockHour;

public interface IBlockHourService {

	List<BlockHour> findAll();
	
	BlockHour findById(final int id);
}
