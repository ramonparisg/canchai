package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IFieldTypeDao;
import com.eiffel.canchai.model.FieldType;

@Repository
@Transactional
public class FieldTypeDao implements IFieldTypeDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(FieldType entity) {
		
	}

	@Override
	public List<FieldType> findAll() {
		// TODO Auto-generated method stub
		return (List<FieldType>) entityManager.createQuery("from FieldType").getResultList();
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
		return (FieldType) entityManager.find(FieldType.class, id);
	}

}
