package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IRolDao;
import com.eiffel.canchai.model.Rol;
import com.eiffel.canchai.service.interfaces.IRolService;

@Service("rolService")
@Transactional
public class RolService implements IRolService{

	@Autowired
	IRolDao rolDao;
	
	@Override
	public void save(Rol entity) {
		rolDao.save(entity);
		
	}

	@Override
	public List<Rol> findAll() {

		return rolDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Rol entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rol findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
