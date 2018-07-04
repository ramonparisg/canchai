package com.eiffel.canchai.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eiffel.canchai.model.BlockHour;
import com.eiffel.canchai.model.Booking;
import com.eiffel.canchai.model.BookingType;
import com.eiffel.canchai.model.Field;
import com.eiffel.canchai.model.Player;
import com.eiffel.canchai.service.interfaces.IBlockHourService;
import com.eiffel.canchai.service.interfaces.IBookingService;
import com.eiffel.canchai.service.interfaces.IBookingTypeService;
import com.eiffel.canchai.service.interfaces.IFieldService;
import com.eiffel.canchai.service.interfaces.IPlayerService;
import com.eiffel.canchai.util.ErrorMsg;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {
	@Autowired
	private IBookingTypeService bookingTypeService;
	
	@Autowired
	private IBookingService bookingService;
	
	@Autowired 
	private IFieldService fieldService;
	
	@Autowired 
	private IPlayerService playerService;
	
	@Autowired 
	private IBlockHourService hourService;
	
	
	@GetMapping("/types")
	public ResponseEntity<List<BookingType>> getTypes(){
		return new ResponseEntity<List<BookingType>>(bookingTypeService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Booking>> getBookings(){
		return new ResponseEntity<List<Booking>>(bookingService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("id") int id){
		Booking b = bookingService.findById(id);
		return new ResponseEntity<Booking>(b,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Booking> makeBooking(@RequestBody Booking booking){
				
		
		booking.setBookingDate(new Date());
		
		booking.setStatus(1);
		
		BookingType bkType = bookingTypeService.findById(booking.getBookingType().getIdbookingType());
		bkType.getBookings().add(booking);
		booking.setBookingType(bkType);
		
		Field field = fieldService.findById(booking.getField().getIdField());
		field.getBookings().add(booking);
		booking.setField(field);
		switch (booking.getBookingType().getIdbookingType()) {
		case 1: //Privada
			booking.setAvailableQuota(0);
			break;
		case 2: //Pública
			booking.setAvailableQuota((booking.getField().getFieldType().getCapacity()) - 1);
			break;
		case 3: //Mixta
			booking.setAvailableQuota((booking.getField().getFieldType().getCapacity()) - booking.getAvailableQuota());
			break;		
		}
		
		
		Player player = playerService.findById(booking.getPlayer().getIdPlayer());
		player.getBookings().add(booking);
		booking.setPlayer(player);
		
		BlockHour hour = hourService.findById(booking.getBlockHour().getIdBlockHour());
		hour.getBookings().add(booking);
		booking.setBlockHour(hour);
		
		bookingService.save(booking);
		
		return new ResponseEntity<Booking>(booking,HttpStatus.OK);
	}

	@GetMapping("/player/{id}")
	public ResponseEntity<List<Booking>> getBookingByPlayerId(@PathVariable("id") int idPlayer){
		List<Booking> bookings = bookingService.findByPlayerId(idPlayer);
		if (bookings.isEmpty()) {
			return new ResponseEntity(new ErrorMsg("Lista vacía"),HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Booking>> getBookingByCriteria(
			@RequestParam("fieldtype") Integer fieldType, 
			@DateTimeFormat(pattern= "yyyy-MM-dd") @RequestParam("date") Date date, 
			@RequestParam("time") Integer time,
			@RequestParam("commune") Integer commune){
		
		List<Booking> bookings = bookingService.findByCriteria(fieldType, date, time, commune);		
		if (bookings.isEmpty()) {
			return new ResponseEntity(new ErrorMsg("Lista vacía"),HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
	}
}
