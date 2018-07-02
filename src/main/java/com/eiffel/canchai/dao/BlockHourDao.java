package com.eiffel.canchai.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IBlockHour;
import com.eiffel.canchai.model.BlockHour;

@Repository
@Transactional
public class BlockHourDao implements IBlockHour {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<BlockHour> findAll() {
		// TODO Auto-generated method stub
		return (List<BlockHour>) entityManager.createQuery("from BlockHour").getResultList();
	}

	@Override
	public BlockHour findById(int id) {
		// TODO Auto-generated method stub
		return (BlockHour) entityManager.find(BlockHour.class, id);
	}

}
