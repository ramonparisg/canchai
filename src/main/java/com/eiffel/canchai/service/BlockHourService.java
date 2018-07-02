package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IBlockHour;
import com.eiffel.canchai.model.BlockHour;
import com.eiffel.canchai.service.interfaces.IBlockHourService;

@Service("blockHourService")
@Transactional
public class BlockHourService implements IBlockHourService {

	@Autowired
	private IBlockHour hourDao;
	
	@Override
	public List<BlockHour> findAll() {

		return hourDao.findAll();
	}

	@Override
	public BlockHour findById(int id) {
		// TODO Auto-generated method stub
		return hourDao.findById(id);
	}

}
