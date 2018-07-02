package com.eiffel.canchai.dao;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractSession {
		
	@Autowired
	private static EntityManagerFactory entityManagerFactory;
	
	
	protected static Session getSession() {		
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		return session;
	}
}