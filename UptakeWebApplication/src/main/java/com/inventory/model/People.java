package com.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vaibhav malhotra
 *
 * Model Class for Table people
 * in H2 Inmemory Database
 */
@Entity
@Table(name = "people")
public class People {
	/**
	 * Primary Key
	 */
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@Column(name="FIRSTNAME")
	private String firstName;

	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="STREETADDRESS")
	private String streetAddress;

	@Column(name="CITY")
	private String city;

	@Column(name="STATE")
	private String state;

	@Column(name="COUNTRY")
	private String country;

	@Column(name="PRIMARYPHONE")
	private String primaryPhone;

	@Column(name="PRIMARYEMAIL")
	private String primaryEmail;

	/**
	 * Default Constructor
	 */
	public People() { }
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param country
	 * @param primaryPhone
	 * 
	 * Constructor taking all arguments
	 * except email address
	 */
	public People(Integer id, String firstName, String lastName,
			String streetAddress, String city, String state, String country,
			String primaryPhone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.primaryPhone = primaryPhone;
		this.primaryEmail = null;
	}

	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param country
	 * @param primaryPhone
	 * @param primaryEmail
	 * 
	 * Constructor taking all
	 * Arguments
	 */
	public People(Integer id, String firstName, String lastName,
			String streetAddress, String city, String state, String country,
			String primaryPhone, String primaryEmail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.primaryPhone = primaryPhone;
		this.primaryEmail = primaryEmail;
	}

	/**
	 * Getters and Setters
	 * For all columns
	 */
	public Integer getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	
}
