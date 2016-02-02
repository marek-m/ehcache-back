package antre.dao;

import java.util.List;

import org.hibernate.Session;

import antre.DaoTemplate;
import antre.db.Meal;

public abstract class MealDao extends DaoTemplate<Meal>{

	public abstract List<Meal> getThisWeek();
	public abstract List<Meal> getThisDay();
	public abstract void saveList(List<Meal> meals, Session session);
	public abstract void dbTest();
	public abstract List<Meal> getByNameFilter(String nameFilter, Session session);
	
}
