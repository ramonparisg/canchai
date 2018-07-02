package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IFieldDao;
import com.eiffel.canchai.model.Field;

@Repository
@Transactional
public class FieldDao implements IFieldDao {
	
	
	 @PersistenceContext
	 private EntityManager entityManager;
	 
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	 
	
	
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
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		
		return (Field) session.find(Field.class, id);
	}

}
