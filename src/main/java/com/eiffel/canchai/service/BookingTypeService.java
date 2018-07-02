package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IBookingTypeDao;
import com.eiffel.canchai.model.BookingType;
import com.eiffel.canchai.service.interfaces.IBookingTypeService;

@Service("bookingTypeService")
@Transactional
public class BookingTypeService implements IBookingTypeService {

	@Autowired
	IBookingTypeDao bookingTypeDao;
	
	@Override
	public void save(BookingType entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BookingType> findAll() {
		// TODO Auto-generated method stub
		return bookingTypeDao.findAll();
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
		return bookingTypeDao.findById(id);
	}

}
