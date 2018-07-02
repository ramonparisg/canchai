package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IMaterialDao;
import com.eiffel.canchai.model.FieldType;
import com.eiffel.canchai.model.Material;

@Repository
@Transactional
public class MaterialDao implements IMaterialDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Material entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Material> findAll() {
		return (List<Material>) entityManager.createQuery("from Material").getResultList();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Material entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Material findById(int id) {
		// TODO Auto-generated method stub
		return (Material) entityManager.find(Material.class, id);
	}

}
