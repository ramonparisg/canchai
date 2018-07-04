package com.eiffel.canchai.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	public void save(Booking booking) {
		/*Calendar c = Calendar.getInstance();
		c.setTime(booking.getGameDate());
		c.add(Calendar.DAY_OF_YEAR, 1);
		booking.setGameDate(c.getTime());		
		*/
		bookingDao.save(booking);
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
		return bookingDao.findById(id);
	}

	@Override
	public List<Booking> findByPlayerId(int id) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		return bookingDao.findByPlayerId(id,new Date());
	}

	@Override
	public List<Booking> findByCriteria(int fieldType, Date date, int time, int commune) {
		// TODO Auto-generated method stub
		return bookingDao.findByCriteria(fieldType, date, time, commune);
	}

}
