package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.FieldDao;
import com.eiffel.canchai.dao.interfaces.IFieldDao;
import com.eiffel.canchai.model.Field;
import com.eiffel.canchai.service.interfaces.IFieldService;

@Service("fieldService")
@Transactional
public class FieldService implements IFieldService {

	@Autowired
	private IFieldDao fieldDao;
	
	@Override
	public void save(Field entity) {
		fieldDao.save(entity);

	}

	@Override
	public List<Field> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Field entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Field findById(int id) {
		return fieldDao.findById(id);
	}

}
