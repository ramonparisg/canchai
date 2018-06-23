package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.ICommuneDao;
import com.eiffel.canchai.model.Commune;

@Repository
@Transactional
public class CommuneDao implements ICommuneDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Commune> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Commune").getResultList();
	}

}
