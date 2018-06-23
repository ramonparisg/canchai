package com.eiffel.canchai.dao.interfaces;

import com.eiffel.canchai.model.Player;

public interface IPlayerDao extends IGenericDao<Player>{
	Player findByUserID(int id);
}
