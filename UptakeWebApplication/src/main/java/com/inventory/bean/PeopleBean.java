package com.inventory.bean;

/**
 * @author vaibhav malhotra
 * 
 * Bean class for Table people
 * in H2 Inmemory Database
 */
public class PeopleBean {
	/**
	 * Instance variables
	 * for all columns
	 */
	private Integer id;	
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String country;
	private String primaryPhone;
	private String primaryEmail;
		
	/**
	 * Getters and Setters
	 * For all columns
	 */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
