package com.eiffel.canchai.service.interfaces;

import java.util.List;

import com.eiffel.canchai.model.Player;

public interface IPlayerService extends IGenericService<Player>{
	Player findByUserID(int id);
	
	List<Player> findByBookingIntegration(int idBooking);
}
