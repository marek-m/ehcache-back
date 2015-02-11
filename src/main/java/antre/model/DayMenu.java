package antre.model;

import java.util.Date;
import java.util.List;

public class DayMenu {

	private Date date;
	private List<MealModel> meals;
	
	public DayMenu(Date date, List<MealModel> meals) {
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

	public List<MealModel> getMeals() {
		return meals;
	}

	public void setMeals(List<MealModel> meals) {
		this.meals = meals;
	}
	
	
}
