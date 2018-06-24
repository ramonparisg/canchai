package com.eiffel.canchai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.eiffel.canchai.model.Commune;
import com.eiffel.canchai.service.interfaces.ICommuneService;

@Controller
@CrossOrigin
public class CommuneController {

	@Autowired
	private ICommuneService communeService;
	
	@GetMapping("/comunas")
	public ResponseEntity<List<Commune>> getAllCommunes(){
		return new ResponseEntity<List<Commune>>(communeService.findAll(),HttpStatus.OK);
	}
}
