package com.eiffel.canchai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eiffel.canchai.model.Field;
import com.eiffel.canchai.model.FieldType;
import com.eiffel.canchai.model.Material;
import com.eiffel.canchai.model.SportCenter;
import com.eiffel.canchai.service.interfaces.IFieldService;
import com.eiffel.canchai.service.interfaces.IFieldTypeService;
import com.eiffel.canchai.service.interfaces.IMaterialService;
import com.eiffel.canchai.service.interfaces.ISportCenterService;

@Controller
@CrossOrigin
@RequestMapping("/field")
public class FieldController {

	@Autowired
	private IFieldService fieldService;
	
	@Autowired
	private IFieldTypeService fieldTypeService;
	
	@Autowired 
	private IMaterialService materialService;
	
	@Autowired
	private ISportCenterService  sportCenterService;
	
	
	
	@GetMapping("/types")
	public ResponseEntity<List<FieldType>> getTypes(){
		List<FieldType> fieldTypes = fieldTypeService.findAll();
		return new ResponseEntity<List<FieldType>>(fieldTypes,HttpStatus.OK); 
	}
	
	@GetMapping("/materials")
	public ResponseEntity<List<Material>> getMaterials(){
		List<Material> materials = materialService.findAll();
		return new ResponseEntity<List<Material>>(materials,HttpStatus.OK); 
	}
	
	@PostMapping("/add")
	public ResponseEntity<Field> addField(@RequestBody Field field){
		field.setStatus((short)1);
		
		FieldType fieldType = fieldTypeService.findById(field.getFieldType().getIdFieldType());		
		fieldType.getFields().add(field);
		field.setFieldType(fieldType);
		
		Material material = materialService.findById(field.getMaterial().getIdMaterial());
		material.getFields().add(field);
		field.setMaterial(material);
		
		SportCenter sportCenter = sportCenterService.findById(field.getSportCenter().getIdSportCenter());
		sportCenter.getFields().add(field);
		field.setSportCenter(sportCenter);
		
		fieldService.save(field);		
		return new ResponseEntity<Field>(field,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Field> findById(@PathVariable("id") Integer id){
		Field field = fieldService.findById(id);
		return new ResponseEntity<Field>(field,HttpStatus.OK);
	}
}
