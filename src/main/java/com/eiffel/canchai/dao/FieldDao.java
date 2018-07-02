package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IFieldDao;
import com.eiffel.canchai.model.Field;

@Repository
@Transactional
public class FieldDao implements IFieldDao {
	
	 @Autowired
	 private EntityManager entityManager;
	
	@Override
	public void save(Field entity) {
		entityManager.persist(entity);
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
		// TODO Auto-generated method stub
		return (Field) entityManager.find(Field.class, id);
	}

}
