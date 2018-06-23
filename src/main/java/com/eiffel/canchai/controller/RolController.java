package com.eiffel.canchai.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.eiffel.canchai.model.Rol;
import com.eiffel.canchai.service.interfaces.IRolService;

@Controller
@RequestMapping("/roles")
public class RolController {
	
	@Autowired
	private IRolService rolService;
	
	@RequestMapping(method = RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<List<Rol>> getRoles(){
		List<Rol> roles = new ArrayList();
		roles = rolService.findAll();
		if (roles.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Rol>>(roles,HttpStatus.OK);
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Rol> getRol(@PathVariable int id){
		Rol rol = rolService.findById(id);
		if (rol == null) {
			return new ResponseEntity<Rol>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Rol>(rol,HttpStatus.OK);
	}
	
	
	@PostMapping(value="/roles", headers="Accept=application/json")
	public ResponseEntity<Void> addRole(@RequestBody Rol role, UriComponentsBuilder builder){
		if (role.getDescription().isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		rolService.save(role);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(builder.path("/v1/roles/{id}").buildAndExpand(role.getIdRol()).toUri());
		
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
}
