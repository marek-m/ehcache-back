package antre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import antre.db.MealModel;
import antre.model.DayMenuModel;
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
	
	@RequestMapping("/initDb")
	public void initDb() throws Exception {
		mealService.initDb();
	}
	
	@RequestMapping("/today")
	public List<MealModel> getThisDay() {
		List<MealModel> result = mealService.getThisDay();
		return result;
	}
	
	@RequestMapping("/week") 
	public List<DayMenuModel> getThisWeek() {
		List<DayMenuModel> result = mealService.getThisWeek();
		return result;
	}

	@Cacheable(value="mealCache", key="#name")
	@RequestMapping("/getByName") 
	public List<MealModel> getByName(@RequestParam(value="name", defaultValue="meal") String name) {
		List<MealModel> result = mealService.getMealsByName(name);
		return result;
	}
	
	@CacheEvict(value="mealCache", allEntries=true)
	@RequestMapping("/updateMealName") 
	public Boolean updateMealName(
			@RequestParam(value="id", defaultValue="1") Long mealId,
			@RequestParam(value="name", defaultValue="meal") String name) {
		Boolean result = mealService.updateMealName(mealId, name);
		return result;
	}
}
