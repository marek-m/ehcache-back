package antre.model;

import java.util.Date;

public class DayMenu {

	private Date date;
	private String soup;
	private String mealA;
	private String mealB;
	private Double priceMeal;
	private Double priceSoup;
	
	
	public DayMenu(Date date, String soup, String mealA, String mealB,
			Double priceMeal, Double priceSoup) {
		super();
		this.date = date;
		this.soup = soup;
		this.mealA = mealA;
		this.mealB = mealB;
		this.priceMeal = priceMeal;
		this.priceSoup = priceSoup;
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
	public String getSoup() {
		return soup;
	}
	public void setSoup(String soup) {
		this.soup = soup;
	}
	public String getMealA() {
		return mealA;
	}
	public void setMealA(String mealA) {
		this.mealA = mealA;
	}
	public String getMealB() {
		return mealB;
	}
	public void setMealB(String mealB) {
		this.mealB = mealB;
	}
	public Double getPriceMeal() {
		return priceMeal;
	}
	public void setPriceMeal(Double priceMeal) {
		this.priceMeal = priceMeal;
	}
	public Double getPriceSoup() {
		return priceSoup;
	}
	public void setPriceSoup(Double priceSoup) {
		this.priceSoup = priceSoup;
	}


	@Override
	public String toString() {
		return "DayMenu [date=" + date + ", soup=" + soup + ", mealA=" + mealA
				+ ", mealB=" + mealB + ", priceMeal=" + priceMeal
				+ ", priceSoup=" + priceSoup + "]";
	}
	
}
