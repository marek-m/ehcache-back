package antre.service;

import java.util.List;

import antre.db.MealModel;
import antre.model.DayMenuModel;

public interface MealService {
	public void initWeek() throws Exception;
	public void initDb() throws Exception;
	public List<MealModel> getThisDay();
	public List<DayMenuModel> getThisWeek();
	
	public void addNewMealToDay(Long dayId);
	public Boolean updateMealName(Long mealId, String newName);
	public List<MealModel> getMealsFromDay(Long dayId);
	public List<MealModel> getAllMeals();
	public List<MealModel> getMealsByName(String name);
}
