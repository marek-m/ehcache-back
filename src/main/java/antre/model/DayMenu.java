package antre.model;

import java.util.Date;
import java.util.List;

public class DayMenu {

	private Date date;
	private List<Meal> meals;
	
	public DayMenu(Date date, List<Meal> meals) {
		super();
		this.date = date;
	}
	
	public DayMenu() {
		super();
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	
	
}
