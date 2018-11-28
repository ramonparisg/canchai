package com.eiffel.canchai.controller;

import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.eiffel.canchai.model.Field;
import com.eiffel.canchai.model.ImageField;
import com.eiffel.canchai.model.Phone;
import com.eiffel.canchai.model.Player;
import com.eiffel.canchai.model.Service;
import com.eiffel.canchai.model.SportCenter;
import com.eiffel.canchai.model.User;
import com.eiffel.canchai.service.interfaces.IImageFieldService;
import com.eiffel.canchai.service.interfaces.IServiceService;
import com.eiffel.canchai.service.interfaces.ISportCenterService;
import com.eiffel.canchai.service.interfaces.IUserService;
import com.eiffel.canchai.util.ErrorMsg;

@Controller
@RequestMapping("/sportcenter")
@CrossOrigin
public class SportCenterController {
	
	@Autowired
	private ISportCenterService  sportCenterService;
	
	@Autowired
	private IServiceService serviceService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IImageFieldService imageService;
	
	public static final String SC_IMAGE_URL = "images/sportcenter/";
	
	//Get all sport centers
	@GetMapping
	public ResponseEntity<List<SportCenter>> findAll(){
		return new ResponseEntity<List<SportCenter>>(sportCenterService.findAll(),HttpStatus.OK);
	}
	
	//Get an specific sport center
	@GetMapping("/{id}")
	public ResponseEntity<SportCenter> findById(@PathVariable("id") int idSportCenter){
		SportCenter sc = sportCenterService.findById(idSportCenter);
		if (sc == null)
			return new ResponseEntity(new ErrorMsg("SportCenter ID " + idSportCenter + " doesn't exist"),HttpStatus.NO_CONTENT);
		return new ResponseEntity<SportCenter>(sc,HttpStatus.OK);
	}
	
	
	
	//Create sportcenters
	@PostMapping("/register")
	public ResponseEntity<?> registerSportCenter(@RequestBody SportCenter sc){		
		if (sc.getEmail() == null || sc.getName()== null || sc.getAddress()== null || sc.getPhones()== null|| sc.getServices()== null) {
			return new ResponseEntity(new ErrorMsg("Check parameters. One or more are missing"),HttpStatus.NO_CONTENT);
		}
			
		for (Phone p : sc.getPhones()) {
			p.setSportCenter(sc);			
		}		
		
		List<Service> services = new ArrayList<>(); 
		for (Service s : sc.getServices()) {
			s = serviceService.findById(s.getIdService());
			s.addSportCenters(sc);
			services.add(s);
		}		
		sc.setServices(services);				
				
		List<User> users = new ArrayList<>();
		for (User user : sc.getUsers()) {
			user = userService.findById(user.getIdUser());
			if (user.getRol().getIdRol() != 3) {
				return new ResponseEntity(new ErrorMsg("El rol no es un rol válido para administrar canchas deportivas"),HttpStatus.NO_CONTENT);
			}
			user.addSportCenters(sc);
			users.add(user);
		}		
		sc.setUsers(users);
						
		sportCenterService.save(sc);
		
		File dir = new File(SC_IMAGE_URL + sc.getIdSportCenter()+"");
		dir.mkdirs();
		
		return new ResponseEntity(sc,HttpStatus.OK);
	}
	
	//Asign users to a SC
	@PostMapping("/users")	
	public ResponseEntity<?> asignUser(@RequestBody SportCenter sc) {
		if (sc.getIdSportCenter() == null || sc.getUsers().size() == 0) {
			return new ResponseEntity(new ErrorMsg("Check parameters. One or more are missing"),HttpStatus.NO_CONTENT);
		}
		
		SportCenter currentSC = sportCenterService.findById(sc.getIdSportCenter());
		if (currentSC == null)
			return new ResponseEntity(new ErrorMsg("ID Sport Center doesn't exist"),HttpStatus.NO_CONTENT);
				
		for (User user : sc.getUsers()) {
			user = userService.findById(user.getIdUser());
			if (user.getRol().getIdRol() != 3) {
				return new ResponseEntity(new ErrorMsg("El rol no es un rol válido para administrar canchas deportivas"),HttpStatus.NO_CONTENT);
			}
			user.addSportCenters(currentSC);
			currentSC.getUsers().add(user);
		}
		
		sportCenterService.update(currentSC);
		return new ResponseEntity(currentSC,HttpStatus.OK);
		
	}
	
	//Asign service to a SC
	@PostMapping("/services")	
	public ResponseEntity<?> asignService(@RequestBody SportCenter sc) {

		if (sc.getIdSportCenter() == null || sc.getServices().size() == 0) {
			return new ResponseEntity(new ErrorMsg("Check parameters. One or more are missing"),HttpStatus.NO_CONTENT);
		}
		
		SportCenter currentSC = sportCenterService.findById(sc.getIdSportCenter());
		if (currentSC == null)
			return new ResponseEntity(new ErrorMsg("ID Sport Center doesn't exist"),HttpStatus.CONFLICT);
				
		for (Service service : sc.getServices()) {
			service = serviceService.findById(service.getIdService());
			if (service == null)
				return new ResponseEntity(new ErrorMsg("ID Service "+ service.getIdService()+ " doesn't exist"),HttpStatus.CONFLICT);
 			service.addSportCenters(currentSC);
			currentSC.getServices().add(service);
		}
		
		sportCenterService.update(currentSC);
		return new ResponseEntity(currentSC,HttpStatus.OK);		
	}
	
	//Search by criteria
	@GetMapping("/search")
	public ResponseEntity<List<SportCenter>> findByCriteria(
			@RequestParam("fieldtype") Integer fieldType, 
			@DateTimeFormat(pattern= "yyyy-MM-dd") @RequestParam("date") Date date, 
			@RequestParam("time") Integer time,
			@RequestParam("commune") Integer commune){
		
		if (fieldType == null || date == null || time == null || commune == null) 
			return new ResponseEntity(new ErrorMsg("Check parameters. One or more are missing"),HttpStatus.NO_CONTENT);
				
		
		//return new ResponseEntity(date,HttpStatus.OK);
		List<SportCenter> sportCenters = sportCenterService.findByCriteria(fieldType, date, time, commune);		
		return new ResponseEntity(sportCenters,HttpStatus.OK);
	}
	
	@GetMapping("/{id}/search")
	public ResponseEntity<List<SportCenter>> findFieldsByCriteria(
			@PathVariable("id") Integer id,
			@RequestParam("fieldtype") Integer fieldType, 
			@DateTimeFormat(pattern= "yyyy-MM-dd") @RequestParam("date") Date date, 
			@RequestParam("time") Integer time,
			@RequestParam("commune") Integer commune){
		
		if (fieldType == null || date == null || time == null || commune == null) 
			return new ResponseEntity(new ErrorMsg("Check parameters. One or more are missing"),HttpStatus.NO_CONTENT);
				
		
		//return new ResponseEntity(date,HttpStatus.OK);
		List<Field> fields = sportCenterService.findFieldByCriteria(fieldType, date, time, commune,id);
		SportCenter sc = sportCenterService.findById(id);
		sc.setFields(fields);
		return new ResponseEntity(sc,HttpStatus.OK);
	}
	
	
	
	
	//Upload image
		@PostMapping(value="/image", headers=("content-type=multipart/form-data"))
		public ResponseEntity<?> uploadSportCenterImages(@RequestParam("id_sc") Integer idSportCenter, @RequestParam("file") MultipartFile[] multipartFile, UriComponentsBuilder componentsBuilder){
			if (idSportCenter == null) {
				return new ResponseEntity(new ErrorMsg("Please set id_player"), HttpStatus.NO_CONTENT);
			}			
			
			SportCenter sc = sportCenterService.findById(idSportCenter);
			if (sc == null) {
				return new ResponseEntity(new ErrorMsg("sc with id_sc: " + idSportCenter + " not found"), HttpStatus.NOT_FOUND);
			}
																
			
			try {						
				
				String fileName = "";
				Path path = null;
				ImageField imgField =  null;				
				ArrayList<byte[]> bytes = new ArrayList<>();
				int i = 0;
				for (MultipartFile mpf : multipartFile) {
					fileName = sc.getImageFields().size() + "-SportCenter" + "." + mpf.getContentType().split("/")[1];
					imgField = new ImageField(SC_IMAGE_URL + sc.getIdSportCenter() + "/" + fileName,sc);
					
					imageService.save(imgField);
					
					sc.getImageFields().add(imgField);
					bytes.add(mpf.getBytes());
					path = Paths.get(SC_IMAGE_URL + sc.getIdSportCenter() + "/" + fileName);
					Files.write(path, bytes.get(i));
					i++;
				}				
				return new ResponseEntity<SportCenter>(sc,HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity(new ErrorMsg("Error during upload: " + multipartFile[0].getOriginalFilename()),HttpStatus.CONFLICT);
			}
					
		}

		
		//Get all Images
		@GetMapping("/{id}/image")		
		public ResponseEntity<?> getImages(@PathVariable("id") int id){
			List<ImageField> images = imageService.findBySportCenter(id);
			try {
				ArrayList<byte[]> byteImages = new ArrayList<>(); 
				for (ImageField imageField : images) {
					Path path = Paths.get(imageField.getUrl());
					File f = path.toFile();
					if (!f.exists()) {
						return new ResponseEntity(new ErrorMsg("image not found"), HttpStatus.NOT_FOUND);
					}					
					byteImages.add(Files.readAllBytes(path));					
				}
				
			
			
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(byteImages.get(0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity(new ErrorMsg("Error during reading image"),HttpStatus.CONFLICT);
			}
		}
				
		
}
