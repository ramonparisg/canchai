package com.eiffel.canchai.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eiffel.canchai.dao.interfaces.ISportCenterDao;
import com.eiffel.canchai.model.Field;
import com.eiffel.canchai.model.SportCenter;


@Repository
@Transactional
public class SportCenterDao implements ISportCenterDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void save(SportCenter entity) {
		entityManager.persist(entity);
	}

	@Override
	public List<SportCenter> findAll() {		
		return (List<SportCenter>) entityManager.createQuery("from SportCenter").getResultList();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SportCenter entity) {
		entityManager.persist(entity);

	}

	@Override
	public SportCenter findById(int id) {
		return (SportCenter) entityManager.find(SportCenter.class,id);
	}

	@Override
	public boolean exist(String email) {
		String hql = "from SportCenter where email = :email";
		int count = entityManager.createQuery(hql).setParameter("email", email).getResultList().size();
		return count > 0 ? true : false;
	}

	@Override
	public SportCenter findByEmail(String email) {		
		return (SportCenter) entityManager.createQuery("from SportCenter where email = :email").setParameter("email", email).getSingleResult();
	}

	@Override
	public List<SportCenter> findByCriteria(int fieldType, Date date, int time, int commune) {					
		/*String HQL = "select distinct(sc) from SportCenter as sc "
				+ "join sc.commune as c "
				+ "join sc.fields as f "
				+ "join f.fieldType as fType "				
				+ "join f.bookings as b "
				//+ "join b.blockHour as h "
				+ "where fType.idFieldType = :fieldType "
				+ "and c.idCommune = :commune "
				+ "not in (select )"
				+ "and ((b.gameDate != :date or b.blockHour.idBlockHour != :time) )";
				//+ "or (b is null))";
				 * 
				 */
		
		String HQL = "select distinct(sc) from SportCenter sc "
				+ " join sc.fields f "
				+ " where f.fieldType.idFieldType = :fieldType"
				+ " and sc.commune.idCommune = :commune";
		List<Field> fieldsWithBooking = findFieldsWithBooking(date, time);
		if (!fieldsWithBooking.isEmpty()) {
			HQL = HQL + " and f not in :fieldsWithBooking";
			return (List<SportCenter>) entityManager.createQuery(HQL)
					.setParameter("fieldType", fieldType)
					.setParameter("commune",commune)
					.setParameter("fieldsWithBooking", fieldsWithBooking)
					.getResultList();
		}else	
			return (List<SportCenter>) entityManager.createQuery(HQL)
					.setParameter("fieldType", fieldType)
					.setParameter("commune",commune)					
					.getResultList();
		
		
		
	}
	
	@Override
	public List<Field> findFieldByCriteria(int fieldType, Date date, int time, int commune, int sc) {									
		String HQL = "select distinct(f) from Field f "
				+ " join f.sportCenter sc"				
				+ " where f.fieldType.idFieldType = :fieldType"
				+ " and sc.idSportCenter = :sc";									
		
		List<Field> fieldsWithBooking = findFieldsWithBooking(date, time);
		if (!fieldsWithBooking.isEmpty()) {
			HQL = HQL + " and f not in :fieldsWithBooking";
			return (List<Field>) entityManager.createQuery(HQL)
					.setParameter("fieldType", fieldType)
					.setParameter("sc", sc)
					.setParameter("fieldsWithBooking", fieldsWithBooking)
					.getResultList();
		}else	
			return (List<Field>) entityManager.createQuery(HQL)
					.setParameter("fieldType", fieldType)
					.setParameter("sc", sc)
					.getResultList();
	}
	
	
	public List<Field> findFieldsWithBooking(Date date, int time) {
		String HQL2 = "select distinct(b.field) from Booking b"
				+ " where b.gameDate = :date"
				+ " and b.blockHour.idBlockHour = :time";
		return (List<Field>) entityManager.createQuery(HQL2).setParameter("date", date).setParameter("time", time).getResultList(); 
	}

}
