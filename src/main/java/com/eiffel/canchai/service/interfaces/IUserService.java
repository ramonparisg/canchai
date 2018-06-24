package com.eiffel.canchai.service.interfaces;

import com.eiffel.canchai.model.User;

public interface IUserService extends IGenericService<User>{

	User login(String email, String pass);
	
	User findByEmail(String email);
	
	boolean userExists(String rut, String email);
}
