package com.eiffel.canchai.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.eiffel.canchai.model.Booking;
import com.eiffel.canchai.model.Game;

public interface IGameDao extends IGenericDao<Game>{
	List<Game> findByPlayerId(int id, Date date);
}
