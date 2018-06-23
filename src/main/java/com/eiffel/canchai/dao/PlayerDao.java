package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IPlayerDao;
import com.eiffel.canchai.model.Player;

@Repository
@Transactional
public class PlayerDao implements IPlayerDao{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Player entity) {
		entityManager.persist(entity);		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Player> findAll() {
		List<Player> players = entityManager.createQuery("from Player").getResultList();
		return players;
	}

	@Override
	public void deleteById(int id) {
		entityManager.remove(findById(id));		
	}

	@Override
	public void update(Player entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Player findByUserID(int id) {		
		return (Player) entityManager.createQuery("from Player where user.idUser = :id").setParameter("id", id).getSingleResult();		
	}
	
	
}
