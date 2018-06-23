package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.ICommuneDao;
import com.eiffel.canchai.model.Commune;
import com.eiffel.canchai.service.interfaces.ICommuneService;

@Service("communeService")
@Transactional
public class CommuneService implements ICommuneService {

	@Autowired
	private ICommuneDao communeDao;
	
	@Override
	public List<Commune> findAll() {
		// TODO Auto-generated method stub
		return communeDao.findAll();
	}

}
