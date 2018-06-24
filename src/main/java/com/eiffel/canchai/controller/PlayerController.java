package com.eiffel.canchai.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	public static final String PLAYER_IMAGE_URL = "images/player/";
	public static final String DEFAULT_IMG= PLAYER_IMAGE_URL + "default.png";
	
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
	
	//Upload image
	@PostMapping(value="/image", headers=("content-type=multipart/form-data"))
	public ResponseEntity<byte[]> uploadTeacherImage(@RequestParam("id_player") Integer idPlayer, @RequestParam("file") MultipartFile multipartFile, UriComponentsBuilder componentsBuilder){
		if (idPlayer == null) {
			return new ResponseEntity(new ErrorMsg("Please set id_player"), HttpStatus.NO_CONTENT);
		}
		
		if (multipartFile.isEmpty()) {
			return new ResponseEntity(new ErrorMsg("Please select a file to upload"), HttpStatus.NO_CONTENT);
		}
		
		Player player = playerService.findById(idPlayer);
		if (player == null) {
			return new ResponseEntity(new ErrorMsg("player with id_player: " + idPlayer + " not found"), HttpStatus.NOT_FOUND);
		}
		
									
		if (!player.getImage().equals(DEFAULT_IMG)) {			
			Path path = Paths.get(player.getImage());
			File f = path.toFile();
			if (f.exists()) {
				f.delete();
			}											
		}
		
		try {			
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateName = dateFormat.format(date);
			
			String fileName = String.valueOf(idPlayer) + "-picturePlayer-" + dateName + "." + multipartFile.getContentType().split("/")[1];
			
			player.setImage(PLAYER_IMAGE_URL + fileName);
			playerService.update(player);

			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(PLAYER_IMAGE_URL + fileName);
			Files.write(path, bytes);
			
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity(new ErrorMsg("Error during upload: " + multipartFile.getOriginalFilename()),HttpStatus.CONFLICT);
		}
				
	}
	
	//Get image
	@GetMapping(value="/{id}/image")
	public ResponseEntity<byte[]> getPlayerImage(@PathVariable("id") Integer idPlayer){
		if (idPlayer == null) {
			return new ResponseEntity(new ErrorMsg("Please set id_player"), HttpStatus.NO_CONTENT);
		}				
		
		Player player = playerService.findById(idPlayer);
		if (player == null) {
			return new ResponseEntity(new ErrorMsg("player with id_player: " + idPlayer + " not found"), HttpStatus.NOT_FOUND);
		}
		
		try {
			Path path = Paths.get(player.getImage());
			File f = path.toFile();
			if (!f.exists()) {
				return new ResponseEntity(new ErrorMsg("image not found"), HttpStatus.NOT_FOUND);
			}
		
		
			byte[] image = Files.readAllBytes(path);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity(new ErrorMsg("Error during reading image"),HttpStatus.CONFLICT);
		}
		
	}
}
