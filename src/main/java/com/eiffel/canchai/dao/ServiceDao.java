package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IServiceDao;
import com.eiffel.canchai.model.Service;

@Repository
@Transactional
public class ServiceDao implements IServiceDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Service entity) {
		entityManager.persist(entity);		
	}

	@Override
	public List<Service> findAll() {
		// TODO Auto-generated method stub
		return (List<Service>) entityManager.createQuery("from Service").getResultList();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Service entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Service findById(int id) {
		return (Service) entityManager.find(Service.class, id);
	}

}
