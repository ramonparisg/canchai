package com.eiffel.canchai.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IPlayerDao;
import com.eiffel.canchai.model.Player;
import com.eiffel.canchai.service.interfaces.IPlayerService;

@Service("playerService")
@Transactional
public class PlayerService implements IPlayerService{
	@Autowired
	IPlayerDao playerDao;
	
	@Override
	public void save(Player entity) {
		playerDao.save(entity);
		
	}

	@Override
	public List<Player> findAll() {
		return playerDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		playerDao.deleteById(id);
	}

	@Override
	public void update(Player entity) {
		
		playerDao.update(entity);
	}

	@Override
	public Player findById(int id) {
		// TODO Auto-generated method stub
		return playerDao.findById(id);
	}

	@Override
	public Player findByUserID(int id) {
		return playerDao.findByUserID(id);
		//return new Player();
	}

}
