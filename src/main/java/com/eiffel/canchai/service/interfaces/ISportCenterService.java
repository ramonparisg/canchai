package com.eiffel.canchai.service.interfaces;

import com.eiffel.canchai.model.SportCenter;

public interface ISportCenterService extends IGenericService<SportCenter>{
	
	boolean exist(String email);
	
	SportCenter findByEmail(String email);
}
