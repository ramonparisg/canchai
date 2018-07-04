package com.eiffel.canchai.service.interfaces;

import java.util.Date;
import java.util.List;

import com.eiffel.canchai.model.Game;

public interface IGameService extends IGenericService<Game>{
	List<Game> findByPlayerId(int id);
}
