package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IImageFieldDao;
import com.eiffel.canchai.model.ImageField;

@Repository
@Transactional
public class ImageFieldDao implements IImageFieldDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(ImageField entity) {
		entityManager.persist(entity);
	}

	@Override
	public ImageField findById(int id) {
		return entityManager.find(ImageField.class, id);
	}

	@Override
	public List<ImageField> findBySportCenter(int idSportCenter) {
		return (List<ImageField>) entityManager.createQuery("select i from ImageField i join SportCenter sc on sc.idSportCenter = :id").setParameter("id", idSportCenter).getResultList();
	}

}
