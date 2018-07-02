package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IBookingDao;
import com.eiffel.canchai.model.Booking;

@Repository
@Transactional
public class BookingDao implements IBookingDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Booking entity) {
		entityManager.persist(entity);
	}

	@Override
	public List<Booking> findAll() {
		// TODO Auto-generated method stub
		return (List<Booking>) entityManager.createQuery("from Booking").getResultList();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Booking entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public Booking findById(int id) {
		// TODO Auto-generated method stub
		return (Booking) entityManager.find(Booking.class, id);
	}

}
