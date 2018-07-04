package com.eiffel.canchai.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.eiffel.canchai.model.Booking;

public interface IBookingDao extends IGenericDao<Booking>{
	
	List<Booking> findByPlayerId(int id, Date date);
	List<Booking> findByCriteria(int fieldType, Date date, int time, int commune);	
}
