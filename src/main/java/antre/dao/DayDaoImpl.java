package antre.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import antre.db.Day;

@Repository
public class DayDaoImpl extends DayDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public void saveList(List<Day> days, Session session) {
		for(Day d : days) {
			save(d, session);
		}
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Day> getThisWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date monday = cal.getTime();
		System.out.println(monday.toString());
		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		Date friday = cal.getTime();
		System.out.println(friday.toString());
		
		Session session = sf.openSession();
		List<Day> list = (List<Day>)session.createCriteria(Day.class)
				.add(Restrictions.between("date", monday, friday))
				.addOrder(Order.asc("date"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		session.close();
		if(list == null) 
			return new ArrayList<Day>();
		return list;
	}

}
