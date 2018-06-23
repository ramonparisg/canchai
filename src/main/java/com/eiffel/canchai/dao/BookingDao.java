package com.eiffel.canchai.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IBookingDao;
import com.eiffel.canchai.model.Booking;

@Repository
@Transactional
public class BookingDao implements IBookingDao {

	@Override
	public void save(Booking entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Booking> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Booking entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Booking findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
