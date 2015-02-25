package antre.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import antre.model.MealAddition;

@Entity
public class OrderItem implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3861084129235735353L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column
	private Integer quantity;
	
	@OneToOne
	private Meal meal;
	
	@Column(nullable = false)
	private Integer mealType = MealType.DAY_MENU_MEAL;
	
	@Column(nullable = false)
	private Integer addition = MealAddition.STANDARD;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	
	
	public OrderItem() {
		super();
	}

	public OrderItem(Long id, Integer quantity, Meal meal, Integer mealType,
			Integer addition, Order order) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.meal = meal;
		this.mealType = mealType;
		this.addition = addition;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Integer getMealType() {
		return mealType;
	}

	public void setMealType(Integer mealType) {
		this.mealType = mealType;
	}

	public Integer getAddition() {
		return addition;
	}

	public void setAddition(Integer addition) {
		this.addition = addition;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", meal="
				+ meal + ", mealType=" + mealType + ", addition=" + addition
				+ ", order=" + order + "]";
	}

	
	
	
	
}
