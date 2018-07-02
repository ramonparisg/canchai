package com.eiffel.canchai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
public class CanchaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanchaiApplication.class, args);
	}
	
}
