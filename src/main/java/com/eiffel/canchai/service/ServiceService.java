package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.eiffel.canchai.dao.interfaces.IServiceDao;
import com.eiffel.canchai.model.Service;
import com.eiffel.canchai.service.interfaces.IServiceService;

@org.springframework.stereotype.Service
@Transactional
public class ServiceService implements IServiceService {

	@Autowired
	private IServiceDao serviceDao;
	
	@Override
	public void save(Service entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Service> findAll() {
		// TODO Auto-generated method stub
		return serviceDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Service entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Service findById(int id) {
		// TODO Auto-generated method stub
		return serviceDao.findById(id);
	}

}
