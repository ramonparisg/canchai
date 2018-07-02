package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IFieldTypeDao;
import com.eiffel.canchai.model.FieldType;
import com.eiffel.canchai.service.interfaces.IFieldTypeService;

@Service("fieldTypeService")
@Transactional
public class FieldTypeService implements IFieldTypeService {

	@Autowired
	private IFieldTypeDao fieldTypeDao; 
	
	@Override
	public void save(FieldType entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FieldType> findAll() {
		return fieldTypeDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(FieldType entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public FieldType findById(int id) {
		// TODO Auto-generated method stub
		return fieldTypeDao.findById(id);
	}

}
