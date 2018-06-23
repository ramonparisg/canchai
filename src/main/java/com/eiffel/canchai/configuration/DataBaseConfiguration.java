package com.eiffel.canchai.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean sessionFactory() {
		LocalContainerEntityManagerFactoryBean sessionFactory = new LocalContainerEntityManagerFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.eiffel.canchai.model");
		//sessionFactory.setHibernateProperties(hibernateProperties());
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		sessionFactory.setJpaVendorAdapter(vendorAdapter);
		return sessionFactory;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("b58dea722dcc36");
		dataSource.setPassword("fdbbae46");
		dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net/heroku_2ce6538e883917f");	
		
		return dataSource;
	}
	
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("show_sql", "true");
		return properties;
	}
	
	/*
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory((SessionFactory) sessionFactory().getObject());
		return hibernateTransactionManager;
	}	
	*/
}
