package antre.service;

import java.util.List;

import antre.db.MealModel;
import antre.model.DayMenuModel;

public interface MealService {
	public void initWeek() throws Exception;
	public List<MealModel> getThisDay();
	public List<DayMenuModel> getThisWeek();
}
