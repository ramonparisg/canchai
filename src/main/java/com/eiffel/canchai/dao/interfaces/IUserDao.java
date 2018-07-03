package com.eiffel.canchai.dao.interfaces;

import java.util.List;

import com.eiffel.canchai.model.User;

public interface IUserDao extends IGenericDao<User>{

	User findByEmail(String email);
	
	boolean userExists(String rut, String email);
	
	List<User> findNotAssigned();
	
	
}
