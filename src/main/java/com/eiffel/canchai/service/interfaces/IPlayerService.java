package com.eiffel.canchai.service.interfaces;

import com.eiffel.canchai.model.Player;

public interface IPlayerService extends IGenericService<Player>{
	Player findByUserID(int id);
}
