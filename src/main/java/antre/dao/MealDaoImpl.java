package antre.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import antre.db.Meal;

@Repository
public class MealDaoImpl extends MealDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Meal> getThisWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date monday = cal.getTime();
		System.out.println(monday.toString());
		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		Date friday = cal.getTime();
		System.out.println(friday.toString());
		
		Session session = sf.openSession();
		List<Meal> list = (List<Meal>)session.createCriteria(Meal.class)
				.add(Restrictions.between("date", monday, friday))
				.addOrder(Order.asc("date"))
				.list();
		session.close();
		if(list == null) 
			return new ArrayList<Meal>();
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Meal> getThisDay() {
		Calendar cal = Calendar.getInstance();
		
		Session session = sf.openSession();
		List<Meal> list = (List<Meal>)session.createCriteria(Meal.class)
				.add(Restrictions.eq("date", cal.getTime()))
				.addOrder(Order.asc("date"))
				.list();
		session.close();
		if(list == null) 
			return new ArrayList<Meal>();
		return list;
	}

	@Override
	public void saveList(List<Meal> meals, Session session) {
		for(Meal m : meals) {
			save(m, session);
		}
		
	}

	@Override
	public void dbTest() {
		Session session = sf.openSession();
		Query q = session.createQuery("SELECT 1 FROM Meal");
		q.uniqueResult();
		session.close();
	}

}
