package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IUserDao;
import com.eiffel.canchai.model.User;

@Repository
@Transactional
public class UserDao implements IUserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(User entity) {
		entityManager.persist(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {		
		return (List<User>) entityManager.createQuery("from User").getResultList();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findById(int id) {
		return entityManager.find(User.class, id);		
	}
	

	@Override
	public User findByEmail(String email) {
		return (User) entityManager.createQuery("from User where email= :email").setParameter("email", email).getSingleResult();				
	}

	@Override
	public boolean userExists(String rut, String email) {
		String hql = "from User where rut = :rut or email = :email";
		int count = entityManager.createQuery(hql).setParameter("rut", rut).setParameter("email", email).getResultList().size();
		return count > 0 ? true : false;		
	}

	@Override
	public List<User> findNotAssigned() { 
		String hql = "select u from User u "				
				+ " left outer join u.sportCenters as sc"				
				+ " join u.rol r where r.idRol = 3"
				+ " and sc is null";
					
		return (List<User>) entityManager.createQuery(hql).getResultList();
		//return (List<User>) entityManager.createNativeQuery(query).getResultList();
	}
	
	
}
