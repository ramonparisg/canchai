package com.eiffel.canchai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eiffel.canchai.model.BlockHour;
import com.eiffel.canchai.service.interfaces.IBlockHourService;

@RestController
@RequestMapping("/blockhour")
@CrossOrigin
public class BlockHourController {
	
	@Autowired
	IBlockHourService hourService;
	
	@GetMapping
	public ResponseEntity<List<BlockHour>> getAll(){
		return new ResponseEntity<List<BlockHour>>(hourService.findAll(),HttpStatus.OK);
	}
}
