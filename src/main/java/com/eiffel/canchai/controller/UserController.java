package com.eiffel.canchai.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.eiffel.canchai.dao.UserDao;
import com.eiffel.canchai.dao.interfaces.IGenericDao;
import com.eiffel.canchai.model.Player;
import com.eiffel.canchai.model.SportCenter;
import com.eiffel.canchai.model.User;
import com.eiffel.canchai.service.interfaces.IPlayerService;
import com.eiffel.canchai.service.interfaces.ISportCenterService;
import com.eiffel.canchai.service.interfaces.IUserService;
import com.eiffel.canchai.util.ErrorMsg;

@Controller
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	IPlayerService playerService;
	
	
	@Autowired
	ISportCenterService sportCenterService;	
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = new ArrayList<>();
		users = userService.findAll();		
		if (users.isEmpty()) 
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id, UriComponentsBuilder builder){		
		User user = userService.findById(id);
		String url = "";
		if (user == null)
			return new ResponseEntity(HttpStatus.NO_CONTENT);
				
		switch(user.getRol().getIdRol()) {
		case 1: //Administrator
			return new ResponseEntity<User>(user,HttpStatus.OK);			
		case 2: //Player
			url = "player";
			break;
		case 3: //SportCenter
			url = "sportCenter";
			break;
		}			
		
		HttpHeaders header = new HttpHeaders();
		header.setLocation(builder.path("/"+url+"/{id}").buildAndExpand(user.getIdUser()).toUri());
		return (ResponseEntity<User>) new ResponseEntity<User>(header,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Player player, UriComponentsBuilder builder){		 
		if (userService.userExists(player.getUser().getRut(), player.getUser().getEmail())) {
			return new ResponseEntity(new ErrorMsg("Usuario ya existe"),HttpStatus.CONFLICT);
		}
		userService.save(player.getUser());
		switch(player.getUser().getRol().getIdRol()) {				
			case 2: //Player
				player.setImage(PlayerController.DEFAULT_IMG);
				playerService.save(player);
				return new ResponseEntity(player,HttpStatus.CREATED);				
		}			
		return new ResponseEntity(player.getUser(),HttpStatus.CREATED);
	}
	
	@PostMapping
	@RequestMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody User u){			
		User user = userService.login(u.getEmail(), u.getPassword());
		if (user == null) 
			return new ResponseEntity(new ErrorMsg("Email y contraseña no coinciden"),HttpStatus.CONFLICT);		
		
		switch(user.getRol().getIdRol()) {
		case 1: //Administrator
			return new ResponseEntity<User>(user,HttpStatus.OK);			
		case 2: //Player			
			Player p = playerService.findByUserID(user.getIdUser());			
			return new ResponseEntity<Player>(p,HttpStatus.OK);			
		case 3: //SportCenter
			SportCenter sc = sportCenterService.findById(user.getSportCenters().get(0).getIdSportCenter());
			return new ResponseEntity<SportCenter>(sc,HttpStatus.OK);
			//return new ResponseEntity<User>(user,HttpStatus.OK);
			//break;
		}	
		
		return new ResponseEntity<User>(user,HttpStatus.OK);			
	}
	
	@PostMapping
	public ResponseEntity<?> findByEmail(@RequestBody User u){			
		User user = userService.findByEmail(u.getEmail());
		if (user == null) 
			return new ResponseEntity(new ErrorMsg("Email no encontrado"),HttpStatus.CONFLICT);
		
		if (user.getRol().getIdRol() != 3) 
			return new ResponseEntity(new ErrorMsg("El rol no corresponde a un centro deportivo"),HttpStatus.CONFLICT);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);			
	}
}
