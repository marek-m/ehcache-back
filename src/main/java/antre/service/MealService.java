package antre.service;

import java.util.List;

import antre.model.MealModel;

public interface MealService {
	public void initWeek() throws Exception;
	public List<MealModel> getThisDay();
	public List<MealModel> getThisWeek();
}
