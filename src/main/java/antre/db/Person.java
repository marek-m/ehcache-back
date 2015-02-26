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
public class Person implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7154461844854900175L;

	@Id
	@GeneratedValue
	// (strategy=GenerationType.SEQUENCE, generator="Meal_SEQ")
	private Long id;

	@Column(nullable = false)
	private String login; // meal name

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date lastLogin;
	
	@Column(nullable = true)
	private String gcmRegistrationId;
	
	

	public Person(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public String getGcmRegistrationId() {
		return gcmRegistrationId;
	}


	public void setGcmRegistrationId(String gcmRegistrationId) {
		this.gcmRegistrationId = gcmRegistrationId;
	}


	public Person() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
