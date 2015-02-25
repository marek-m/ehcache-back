package antre.db;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Day implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3747686366016572869L;

	@Id
    @GeneratedValue
	private Long id;

	@Column
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;
	
	@OneToMany(mappedBy="day", cascade = CascadeType.ALL, fetch=FetchType.EAGER)	
	private Set<Meal> meals;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		for(Meal m : meals) {
			m.setDay(this);
		}
		this.meals = meals;
	}
	
	
	
}
