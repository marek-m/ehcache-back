package antre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antre.model.MealModel;
import antre.service.MealService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	MealService mealService;
	
	@RequestMapping("/initWeek")
	public void initWeek() throws Exception {
		mealService.initWeek();
	}
	
	@RequestMapping("/today")
	public List<MealModel> getThisDay() {
		List<MealModel> result = mealService.getThisDay();
		return result;
	}
	
	@RequestMapping("/week") 
	public List<MealModel> getThisWeek() {
		List<MealModel> result = mealService.getThisWeek();
		return result;
	}
}
