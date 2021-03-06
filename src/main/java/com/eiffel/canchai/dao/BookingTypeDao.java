package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IBookingTypeDao;
import com.eiffel.canchai.model.BookingType;

@Repository
@Transactional
public class BookingTypeDao implements IBookingTypeDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(BookingType entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BookingType> findAll() {
		// TODO Auto-generated method stub
		return (List<BookingType>) entityManager.createQuery("from BookingType").getResultList();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(BookingType entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public BookingType findById(int id) {
		// TODO Auto-generated method stub
		return (BookingType) entityManager.find(BookingType.class, id);
	}

}
