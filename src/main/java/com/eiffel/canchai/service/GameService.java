package com.eiffel.canchai.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiffel.canchai.dao.interfaces.IGameDao;
import com.eiffel.canchai.model.Game;
import com.eiffel.canchai.service.interfaces.IGameService;

@Service("gameService")
@Transactional
public class GameService implements IGameService {

	@Autowired
	private IGameDao gameDao;
	
	@Override
	public void save(Game entity) {
		// TODO Auto-generated method stub
		gameDao.save(entity);

	}

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Game entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Game findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> findByPlayerId(int id) {
		// TODO Auto-generated method stub
		return gameDao.findByPlayerId(id, new Date());
	}

}
