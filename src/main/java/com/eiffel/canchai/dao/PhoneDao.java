package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IPhoneDao;
import com.eiffel.canchai.model.Phone;

@Repository
@Transactional
public class PhoneDao implements IPhoneDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Phone entity) {
		entityManager.persist(entity);

	}

	@Override
	public List<Phone> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Phone entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Phone findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
