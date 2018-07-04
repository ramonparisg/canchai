package com.eiffel.canchai.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IGameDao;
import com.eiffel.canchai.model.Booking;
import com.eiffel.canchai.model.Game;

@Repository
@Transactional
public class GameDao implements IGameDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(Game entity) {
		entityManager.persist(entity);
	}

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return (List<Game>) entityManager.createQuery("from Game");
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Game entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Game findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> findByPlayerId(int id, Date date) {
		// TODO Auto-generated method stub
		return (List<Game>) entityManager.createQuery("select g from Game g "
				+ "join g.player p "
				+ "join g.booking b "
				+ "where p.idPlayer = :id "
				+ "and b.gameDate >= :date")
				.setParameter("id", id)
				.setParameter("date", date)
				.getResultList();
	}

}
