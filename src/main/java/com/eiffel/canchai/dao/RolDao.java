package com.eiffel.canchai.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IRolDao;
import com.eiffel.canchai.model.Rol;

@Repository
@Transactional
public class RolDao implements IRolDao{
	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	public void save(Rol entity) {
		entityManager.persist(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rol> findAll() {				
		return (List<Rol>) entityManager.createQuery("from Rol").getResultList();
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
