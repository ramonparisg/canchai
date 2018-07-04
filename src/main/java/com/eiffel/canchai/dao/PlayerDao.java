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
	public void update(Player p) {
		/*
		Player currentPlayer = findById(p.getIdPlayer());
		currentPlayer.setBirthDate(p.getBirthDate());
		currentPlayer.getCommune().setIdCommune(p.getCommune().getIdCommune());
		currentPlayer.setDescription(p.getDescription());
		currentPlayer.getUser().setPhone(p.getUser().getPhone());
		*/
		entityManager.merge(p);
	}

	@Override
	public Player findById(int id) {
		// TODO Auto-generated method stub
		return (Player) entityManager.find(Player.class, id);
	}
	
	@Override
	public Player findByUserID(int id) {		
		return (Player) entityManager.createQuery("from Player where user.idUser = :id").setParameter("id", id).getSingleResult();		
	}
	@Override
	public List<Player> findByBookingIntegration(int idBooking) {
		// TODO Auto-generated method stub
		String hql = "select p from Player as p"
				+ " join p.games as g"
				+ " join g.booking as b"
				+ " where b.idBooking = :idBooking";				
		return (List<Player>) entityManager.createQuery(hql).setParameter("idBooking", idBooking).getResultList();
	}
	
	
}
