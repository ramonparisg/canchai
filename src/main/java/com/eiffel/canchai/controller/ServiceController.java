package com.eiffel.canchai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eiffel.canchai.model.Service;
import com.eiffel.canchai.service.interfaces.IServiceService;
import com.eiffel.canchai.util.ErrorMsg;

@Controller
@RequestMapping("/services")
@CrossOrigin
public class ServiceController {
	
	@Autowired
	private IServiceService serviceService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Service> findById(@PathVariable("id") Integer id){
		Service s = serviceService.findById(id);
		if (s == null )
			return new ResponseEntity(new ErrorMsg("ID doesnt exist"),HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<Service>(s, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Service>> findAll(){		
		
		return new ResponseEntity<List<Service>>(serviceService.findAll(), HttpStatus.OK);
	}
}
