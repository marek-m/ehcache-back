package antre.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import antre.model.MealAddition;

@Entity
// @SequenceGenerator(name="Meal_SEQ", sequenceName="Meal_SEQ", initialValue=1,
// allocationSize=1)
public class Meal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5190761033434267677L;

	@Id
	@GeneratedValue
	// (strategy=GenerationType.SEQUENCE, generator="Meal_SEQ")
	private Long id;

	@Column(nullable = false)
	private String name; // meal name

	@Column(nullable = true, length = 5000)
	private String images; // ONE STRING where token = '|'

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "day_id")
	private Day day;

	
	
	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Meal() {
		super();
	}

	public Meal(String name, String images, Double price, Date date) {
		super();
		this.name = name;
		this.images = images;
		this.price = price;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
