package com.eiffel.canchai.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.eiffel.canchai.model.Field;
import com.eiffel.canchai.model.SportCenter;

public interface ISportCenterDao extends IGenericDao<SportCenter>{
	
	List<SportCenter >findByCriteria(int fieldType, Date date, int time, int commune);
	
	List<Field>findFieldByCriteria(int fieldType, Date date, int time, int commune, int sc);
	
	boolean exist(String email);
	
	SportCenter findByEmail(String email);		
		
}
