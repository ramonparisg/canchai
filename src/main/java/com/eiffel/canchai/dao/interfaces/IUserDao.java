package com.eiffel.canchai.dao.interfaces;

import com.eiffel.canchai.model.User;

public interface IUserDao extends IGenericDao<User>{

	User findByEmail(String email);
}
