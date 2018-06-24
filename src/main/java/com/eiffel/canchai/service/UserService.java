package com.eiffel.canchai.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IUserDao;
import com.eiffel.canchai.model.User;
import com.eiffel.canchai.service.interfaces.IUserService;

@Service("userService")
@Transactional
public class UserService implements IUserService {

	@Autowired
	IUserDao userDao;
	
	@Override
	public void save(User entity) {
		userDao.save(entity);
	}

	@Override
	public List<User> findAll() {		
		return userDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public User login(String email, String pass) {
		if (userExists("", email)) {
			User u = findByEmail(email);
			if (u.getPassword().equals(pass)) 
				return u;
			else
				return null;
		}
		
		return null;
	}

	@Override
	public User findByEmail(String email) {		
		return userDao.findByEmail(email);
	}

	@Override
	public boolean userExists(String rut, String email) {		
		return userDao.userExists(rut, email);
	}

}
