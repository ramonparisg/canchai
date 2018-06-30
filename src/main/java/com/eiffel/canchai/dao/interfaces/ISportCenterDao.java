package com.eiffel.canchai.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.eiffel.canchai.model.SportCenter;

public interface ISportCenterDao extends IGenericDao<SportCenter>{
	
	List<SportCenter >findByCriteria(int fieldType, Date date, int time, int commune);
	
	boolean exist(String email);
	
	SportCenter findByEmail(String email);		
		
}
