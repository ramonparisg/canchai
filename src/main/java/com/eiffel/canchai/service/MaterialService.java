package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IMaterialDao;
import com.eiffel.canchai.model.Material;
import com.eiffel.canchai.service.interfaces.IMaterialService;

@Service("materialService")
@Transactional
public class MaterialService implements IMaterialService {

	@Autowired
	private IMaterialDao materialDao;
	
	@Override
	public void save(Material entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Material> findAll() {
		// TODO Auto-generated method stub
		return materialDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Material entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Material findById(int id) {
		// TODO Auto-generated method stub
		return materialDao.findById(id);
	}

}
