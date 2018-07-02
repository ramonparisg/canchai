package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IImageFieldDao;
import com.eiffel.canchai.model.ImageField;
import com.eiffel.canchai.service.interfaces.IImageFieldService;

@Service("imageFieldService")
@Transactional
public class ImageFieldService implements IImageFieldService {

	@Autowired
	private IImageFieldDao imageDao;
	
	@Override
	public void save(ImageField entity) {
		imageDao.save(entity);		
	}

	@Override
	public List<ImageField> findBySportCenter(int idSportCenter) {
		// TODO Auto-generated method stub
		return imageDao.findBySportCenter(idSportCenter);
	}

	@Override
	public ImageField findById(int id) {
		// TODO Auto-generated method stub
		return imageDao.findById(id);
	}

	

}
