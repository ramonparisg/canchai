package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.ISportCenterDao;
import com.eiffel.canchai.model.SportCenter;
import com.eiffel.canchai.service.interfaces.ISportCenterService;

@Service("sportCenterService")
@Transactional
public class SportCenterService implements ISportCenterService {

	@Autowired
	private ISportCenterDao sportCenterDao;
	
	@Override
	public void save(SportCenter entity) {
		sportCenterDao.save(entity);

	}

	@Override
	public List<SportCenter> findAll() {		
		return sportCenterDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SportCenter entity) {
		sportCenterDao.update(entity);
	}

	@Override
	public SportCenter findById(int id) {
		// TODO Auto-generated method stub
		return sportCenterDao.findById(id);
	}
	
	@Override
	public boolean exist(String email) {
		return sportCenterDao.exist(email);
	}
	
	@Override
	public SportCenter findByEmail(String email) {
		
		return exist(email) ? (SportCenter) sportCenterDao.findByEmail(email) : null;
	}

}
