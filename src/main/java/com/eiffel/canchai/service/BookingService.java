package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IBookingDao;
import com.eiffel.canchai.model.Booking;
import com.eiffel.canchai.service.interfaces.IBookingService;

@Service("bookingService")
@Transactional
public class BookingService implements IBookingService {

	@Autowired
	private IBookingDao bookingDao;
	
	@Override
	public void save(Booking entity) {
		bookingDao.save(entity);
	}

	@Override
	public List<Booking> findAll() {
		// TODO Auto-generated method stub
		return bookingDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		bookingDao.deleteById(id);

	}

	@Override
	public void update(Booking entity) {
		// TODO Auto-generated method stub
		bookingDao.update(entity);
	}

	@Override
	public Booking findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
