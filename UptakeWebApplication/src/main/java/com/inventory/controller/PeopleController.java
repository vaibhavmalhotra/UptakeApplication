package com.inventory.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inventory.bean.PeopleBean;
import com.inventory.model.People;

/**
 * @author vaibhav malhotra
 * 
 * General Super Class 
 * for both Controllers
 */
class PeopleController {

	/**
	 * @param peopleBean
	 * @return
	 * 
	 * Creating People object
	 * from peopleBean object
	 */

	protected People prepareModel(PeopleBean peopleBean) {
		  People people = new People();
		  people.setFirstName(peopleBean.getFirstName());
		  people.setLastName(peopleBean.getLastName());
		  people.setStreetAddress(peopleBean.getStreetAddress());
		  people.setCity(peopleBean.getCity());
		  people.setState(peopleBean.getState());
		  people.setCountry(peopleBean.getCountry());
		  people.setPrimaryPhone(peopleBean.getPrimaryPhone());
		  people.setPrimaryEmail(peopleBean.getPrimaryEmail());
		  
		  return people;
	 }

	/**
	 * @param peopleBeans
	 * @return
	 * 
	 * Creating list of People objects
	 * from list of PeopleBean objects
	 */

	protected List<People> prepareModelAll(List<PeopleBean> peopleBeans) {
		List<People> peoples = new ArrayList<People>();
		for(PeopleBean peopleBean : peopleBeans) {
			peoples.add(prepareModel(peopleBean));
		}
		return peoples;
	 }

	/**
	 * @param peoples
	 * @return
	 * 
	 * Creating list of peopleBean objects
	 * from list of People objects
	 */
	protected List<PeopleBean> prepareListofBean(List<People> peoples) {
		  List<PeopleBean> beans = null;
		  if(peoples != null && !peoples.isEmpty()){
		   beans = new ArrayList<PeopleBean>();
		   PeopleBean bean;
		   for(People people : peoples){
			    bean = new PeopleBean();
			    bean.setId(people.getId());
			    bean.setFirstName(people.getFirstName());
			    bean.setLastName(people.getLastName());
			    bean.setStreetAddress(people.getStreetAddress());
			    bean.setCity(people.getCity());
			    bean.setState(people.getState());
			    bean.setCountry(people.getCountry());
			    bean.setPrimaryPhone(people.getPrimaryPhone());
			    bean.setPrimaryEmail(people.getPrimaryEmail());
			    beans.add(bean);
		   }
		 }
		  return beans;
	 }

	/**
	 * @param peopleBean
	 * @return
	 * 
	 * For validating the Update People
	 * fields so that they satisfy
	 * all the requirements
	 */

	protected String checkUpdateErrors(PeopleBean peopleBean) {
		 if(peopleBean.getFirstName() != null && peopleBean.getFirstName().trim().equals("")) {
			 return "Error: First Name cannot be empty!";
		 } else if(peopleBean.getLastName() != null && peopleBean.getLastName().trim().equals("")) {
			 return "Error: Last Name cannot be empty!";
		 } else if(peopleBean.getStreetAddress() != null && peopleBean.getStreetAddress().trim().equals("")) {
			 return "Error: Street Address cannot be empty!";
		 } else if(peopleBean.getCity() != null && peopleBean.getCity().trim().equals("")) {
			 return "Error: City cannot be empty!";
		 } else if(peopleBean.getState() != null && peopleBean.getState().trim().equals("")) {
			 return "Error: State cannot be empty!";
		 } else if(peopleBean.getCountry() != null && peopleBean.getCountry().trim().equals("")) {
			 return "Error: Country cannot be empty!";
		 } else if(peopleBean.getPrimaryPhone() != null && peopleBean.getPrimaryPhone().trim().equals("")) {
			 return "Error: Primary Phone cannot be empty!";
		 } else if(peopleBean.getPrimaryPhone() != null && peopleBean.getPrimaryPhone().length() != 10) {
			 return "Error: Primary Phone should contain exactly 10 digits!";
		 } else if(peopleBean.getPrimaryEmail() != null && peopleBean.getPrimaryEmail().trim().equals("")) {
			 return "Error: Primary Email cannot be empty!";
		 } else if(peopleBean.getPrimaryEmail() != null && peopleBean.getPrimaryEmail().indexOf('@') == -1) {
			 return "Error: Primary Email is not in the proper format!";
		 }
		 
		 if(peopleBean.getPrimaryPhone() != null) {
			 String phone = peopleBean.getPrimaryPhone();
			 for(int i=0; i<phone.length(); i++) {
				 if(phone.charAt(i) < 48 || phone.charAt(i) > 57) {
					 return "Error: Primary Phone can contain numbers only!";
				 }
			 }
		 }
		 
		 return null;
	 }
	
	/**
	 * @param peopleBean
	 * @return
	 * 
	 * For validating the new People
	 * fields so that they satisfy
	 * all the requirements
	 */

	protected String checkErrors(PeopleBean peopleBean) {
		 if(peopleBean.getFirstName() == null || peopleBean.getFirstName().trim().equals("")) {
			 return "Error: First Name is a required field!";
		 } else if(peopleBean.getLastName() == null || peopleBean.getLastName().trim().equals("")) {
			 return "Error: Last Name is a required field!";
		 } else if(peopleBean.getStreetAddress() == null || peopleBean.getStreetAddress().trim().equals("")) {
			 return "Error: Street Address is a required field!";
		 } else if(peopleBean.getCity() == null || peopleBean.getCity().trim().equals("")) {
			 return "Error: City is a required field!";
		 } else if(peopleBean.getState() == null || peopleBean.getState().trim().equals("")) {
			 return "Error: State is a required field!";
		 } else if(peopleBean.getCountry() == null || peopleBean.getCountry().trim().equals("")) {
			 return "Error: Country is a required field!";
		 } else if(peopleBean.getPrimaryPhone() == null || peopleBean.getPrimaryPhone().trim().equals("")) {
			 return "Error: Primary Phone is a required field!";
		 } else if(peopleBean.getPrimaryPhone().length() != 10) {
			 return "Error: Primary Phone should contain exactly 10 digits!";
		 } else if(peopleBean.getPrimaryEmail() == null || peopleBean.getPrimaryEmail().trim().equals("")) {
			 return "Error: Primary Email is a required field!";
		 } else if(peopleBean.getPrimaryEmail().indexOf('@') == -1) {
			 return "Error: Primary Email is not in the proper format!";
		 }
		 
		 String phone = peopleBean.getPrimaryPhone();
		 for(int i=0; i<phone.length(); i++) {
			 if(phone.charAt(i) < 48 || phone.charAt(i) > 57) {
				 return "Error: Primary Phone can contain numbers only!";
			 }
		 }
		 
		 return null;
	 }
	
	/**
	 * @param ex
	 * @return
	 * 
	 * For Exception Handling
	 */
	@ExceptionHandler(JsonProcessingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleJsonException(Exception ex) {
	    return "Bad Request : " + ex.getMessage();
	}
	
	/**
	 * @param ex
	 * @return
	 * 
	 * For Exception Handling
	 */
	@ExceptionHandler(HibernateException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleHibernateException(Exception ex) {
	    return "Internal Server Error : " + ex.getMessage();
	}
	
	/**
	 * @param ex
	 * @return
	 * 
	 * For Exception Handling
	 */
	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleIOException(Exception ex) {
	    return "Internal Server Error : " + ex.getMessage();
	}
}
