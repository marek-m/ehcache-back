package antre.model;

import java.util.Date;
import java.util.List;

public class DayMenu {

	private Date date;
	
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
	
}
