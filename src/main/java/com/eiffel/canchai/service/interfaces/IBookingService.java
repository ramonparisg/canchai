package com.eiffel.canchai.service.interfaces;

import java.util.Date;
import java.util.List;

import com.eiffel.canchai.model.Booking;

public interface IBookingService extends IGenericService<Booking>{
	
	List<Booking> findByPlayerId(int id);
	List<Booking> findByCriteria(int fieldType, Date date, int time, int commune);
}
