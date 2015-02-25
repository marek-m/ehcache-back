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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Order implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1732083588651856416L;

	@Id
    @GeneratedValue
	private Long id;

	@Column
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date date;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, fetch=FetchType.EAGER)	
	private Set<OrderItem> items;

	@OneToOne
	private Person person;
	
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

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		for(OrderItem oi : items) {
			oi.setOrder(this);
		}
		this.items = items;
	}
}
