package com.eiffel.canchai.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eiffel.canchai.model.Booking;
import com.eiffel.canchai.model.Game;
import com.eiffel.canchai.model.Player;
import com.eiffel.canchai.service.interfaces.IBookingService;
import com.eiffel.canchai.service.interfaces.IGameService;
import com.eiffel.canchai.service.interfaces.IPlayerService;
import com.eiffel.canchai.util.ErrorMsg;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IPlayerService playerService;
	
	@Autowired
	private IBookingService bookingService;
	
	@PostMapping
	public ResponseEntity<Game> incorporeToABooking(@RequestBody Game game){
		Booking booking = bookingService.findById(game.getBooking().getIdBooking());
		if (booking.getAvailableQuota() < 1) {
			return new ResponseEntity(new ErrorMsg("No hay espacio para integrarse"),HttpStatus.CONFLICT);
		}
		
		if (booking.getPlayer().getIdPlayer() == game.getPlayer().getIdPlayer()) {
			return new ResponseEntity(new ErrorMsg("El creador de la partida no puede integrarse a su propia partida"),HttpStatus.CONFLICT);
		}
		
		
		booking.setAvailableQuota(booking.getAvailableQuota()-1);
		bookingService.save(booking);		
		game.setBooking(booking);
		
		Player player = playerService.findById(game.getPlayer().getIdPlayer());
		game.setPlayer(player);
		
		game.setIntegrationDate(new Date());
		game.setStatus(1);
		
		gameService.save(game);
		return new ResponseEntity<Game>(game,HttpStatus.OK);	
	}
	
	@GetMapping("/player/{id}")
	public ResponseEntity<List<Game>> findGamesByPlayerId(@PathVariable("id") int id){
		List<Game> games = gameService.findByPlayerId(id);
		if (games.isEmpty()) {
			return new ResponseEntity(new ErrorMsg("No tiene integraciones"),HttpStatus.NO_CONTENT);
		}
		
		
		return new ResponseEntity<List<Game>>(games,HttpStatus.OK);
		
	}
}
