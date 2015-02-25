package antre.model;

import java.util.Date;
import java.util.List;

import antre.db.MealModel;

public class DayMenuModel {

	private Date date;
	private List<MealModel> meals;
	
	public DayMenuModel(Date date, List<MealModel> meals) {
		super();
		this.date = date;
	}
	
	public DayMenuModel() {
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
