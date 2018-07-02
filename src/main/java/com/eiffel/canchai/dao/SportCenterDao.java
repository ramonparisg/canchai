package com.eiffel.canchai.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.ISportCenterDao;
import com.eiffel.canchai.model.SportCenter;


@Repository
@Transactional
public class SportCenterDao implements ISportCenterDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void save(SportCenter entity) {
		entityManager.persist(entity);
	}

	@Override
	public List<SportCenter> findAll() {		
		return (List<SportCenter>) entityManager.createQuery("from SportCenter").getResultList();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SportCenter entity) {
		entityManager.persist(entity);

	}

	@Override
	public SportCenter findById(int id) {
		return (SportCenter) entityManager.find(SportCenter.class,id);
	}

	@Override
	public boolean exist(String email) {
		String hql = "from SportCenter where email = :email";
		int count = entityManager.createQuery(hql).setParameter("email", email).getResultList().size();
		return count > 0 ? true : false;
	}

	@Override
	public SportCenter findByEmail(String email) {		
		return (SportCenter) entityManager.createQuery("from SportCenter where email = :email").setParameter("email", email).getSingleResult();
	}

	@Override
	public List<SportCenter> findByCriteria(int fieldType, Date date, int time, int commune) {
		
		/*
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();		
		CriteriaQuery<SportCenter> criteria = builder.createQuery(SportCenter.class);
		Root<SportCenter> root = criteria.from(SportCenter.class);
		criteria.select(root);
		//criteria.where(builder.equal("", y))
		*/
		
		String q = "SELECT SportCenter FROM ";
		
		return null;
	}

}
