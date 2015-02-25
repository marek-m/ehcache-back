package antre.dao;

import java.util.List;

import org.hibernate.Session;

import antre.DaoTemplate;
import antre.db.Day;

public abstract class DayDao extends DaoTemplate<Day>{

	public abstract void saveList(List<Day> meals, Session session);
	public abstract List<Day> getThisWeek();
	
}
