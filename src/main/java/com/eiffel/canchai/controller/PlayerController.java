package com.eiffel.canchai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eiffel.canchai.model.Player;
import com.eiffel.canchai.model.User;
import com.eiffel.canchai.service.interfaces.IPlayerService;
import com.eiffel.canchai.util.ErrorMsg;

@Controller
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {
	@Autowired
	private IPlayerService playerService;
	
	//Going to the profile
	@PostMapping(value="/profile")
	public ResponseEntity<Player> getPlayer(@RequestBody Player p){
		Player player = playerService.findById(p.getIdPlayer());
		if (player == null)
			return new ResponseEntity(new ErrorMsg("Jugador no existe"),HttpStatus.NO_CONTENT);
		
		player.getUser().setPassword("");
		return new ResponseEntity<Player>(player,HttpStatus.OK);
	}
	
	//Update
	@PatchMapping(value="/profile")
	public ResponseEntity<Player> updatePlayer(@RequestBody Player p){
		Player currentPlayer = playerService.findById(p.getIdPlayer());
		if (currentPlayer == null)
			return new ResponseEntity(new ErrorMsg("Jugador no existe"),HttpStatus.NO_CONTENT);
		
		currentPlayer.setBirthDate(p.getBirthDate());
		currentPlayer.setCommune(p.getCommune());
		currentPlayer.setDescription(p.getDescription());
		//currentPlayer.getUser().setPassword(p.getUser().getPassword());
		currentPlayer.getUser().setPhone(p.getUser().getPhone());				
		playerService.update(currentPlayer);
		currentPlayer.getUser().setPassword("");
		return new ResponseEntity<Player>(currentPlayer,HttpStatus.OK);
	}
	
	
}
