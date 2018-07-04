package com.eiffel.canchai.dao.interfaces;

import java.util.List;

import com.eiffel.canchai.model.Player;

public interface IPlayerDao extends IGenericDao<Player>{
	Player findByUserID(int id);
	
	List<Player> findByBookingIntegration(int idBooking);
}
