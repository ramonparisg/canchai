package com.eiffel.canchai.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.IBookingDao;
import com.eiffel.canchai.model.Booking;
import com.eiffel.canchai.model.SportCenter;

@Repository
@Transactional
public class BookingDao implements IBookingDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Booking entity) {
		entityManager.persist(entity);
	}

	@Override
	public List<Booking> findAll() {
		// TODO Auto-generated method stub
		return (List<Booking>) entityManager.createQuery("from Booking").getResultList();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Booking entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public Booking findById(int id) {
		// TODO Auto-generated method stub
		return (Booking) entityManager.find(Booking.class, id);
	}

	@Override
	public List<Booking> findByPlayerId(int id, Date date) {
		// TODO Auto-generated method stub
		return (List<Booking>) entityManager.createQuery("select b from Booking b join b.player p "
				+ "where p.idPlayer = :id"
				+ " and b.gameDate >= :date").setParameter("id", id).setParameter("date", date).getResultList();
	}

	@Override
	public List<Booking> findByCriteria(int fieldType, Date date, int time, int commune) {
		String HQL = "select distinct(b) from Booking as b "
				+ "join b.field as f "				
				+ "join f.fieldType as fType "
				+ "join f.sportCenter as sc "
				+ "join sc.commune as c "
				//+ "join b.blockHour as h "
				+ "where fType.idFieldType = :fieldType "
				+ "and c.idCommune = :commune "
				+ "and b.gameDate = :date "
				+ "and b.blockHour.idBlockHour = :time "
				+ "and b.availableQuota > 0";
		
		return (List<Booking>) entityManager.createQuery(HQL)
				.setParameter("fieldType", fieldType)
				.setParameter("commune",commune)
				.setParameter("date",date)
				.setParameter("time",time).getResultList();
	}

}
