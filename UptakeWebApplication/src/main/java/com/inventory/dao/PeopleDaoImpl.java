package com.inventory.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inventory.bean.PeopleBean;
import com.inventory.model.People;

/**
 * @author vaibhav malhotra
 * 
 * DAO Class for the
 * People Model Class
 *
 */
@Repository
public class PeopleDaoImpl implements PeopleDao {

	/**
	 * SessionFactory variable 
	 * of Hibernate Framework 
	 */
	@Autowired
	private SessionFactory session;
	
	 /* 
	 * For adding a specific person to the Table people
	 */
	public People add(People people) {
		Integer id = (Integer) session.getCurrentSession().save(people);
		return get(id);
	}

	/* 
	 * For adding a list of people to the Table people
	 */
	public List<People> addAll(List<People> peoples) {
		List<People> newPeople = new ArrayList<People>();
		Session s = session.getCurrentSession();
		for(People people : peoples) {
			Integer id = (Integer) s.save(people);
			s.flush();
			s.clear();
			newPeople.add(get(id));
		}
		
		return newPeople;
	}
	
	/* 
	 * For retrieving a specific person from 
	 * the Table people with the given id
	 */
	public People get(Integer id) {
		return (People)session.getCurrentSession().get(People.class, id);
	}
	
	/* 
	 * For updating a specific person with 
	 * given Id in the Table people
	 */
	public People update(PeopleBean people, Integer editBeanId) {
		StringBuilder updateQuery = new StringBuilder("UPDATE People SET");
		
		if(people.getFirstName() != null) {
			updateQuery.append(" FIRSTNAME = '" + people.getFirstName() + "',");
		}
		if(people.getLastName() != null) {
			updateQuery.append(" LASTNAME = '" + people.getLastName() + "',");
		}
		if(people.getStreetAddress() != null) {
			updateQuery.append(" STREETADDRESS = '" + people.getStreetAddress() + "',");
		}
		if(people.getCity() != null) {
			updateQuery.append(" CITY = '" + people.getCity() + "',");
		}
		if(people.getState() != null) {
			updateQuery.append(" STATE = '" + people.getState() + "',");
		}
		if(people.getCountry() != null) {
			updateQuery.append(" COUNTRY = '" + people.getCountry() + "',");
		}
		if(people.getPrimaryPhone() != null) {
			updateQuery.append(" PRIMARYPHONE = '" + people.getPrimaryPhone() + "',");
		}
		if(people.getPrimaryEmail() != null) {
			updateQuery.append(" PRIMARYEMAIL = '" + people.getPrimaryEmail() + "',");
		}
		
		if(updateQuery.charAt(updateQuery.length()-1) == ',') {
			updateQuery.deleteCharAt(updateQuery.length()-1);
			updateQuery.append(" WHERE ID = '" + editBeanId + "'");
			session.getCurrentSession().createQuery(updateQuery.toString()).executeUpdate();
		}
		
		return get(editBeanId);
	}
	
	/* 
	 * For deleting a specific person from the Table people
	 */
	public People delete(Integer id) {
		People people = get(id);
		session.getCurrentSession().createQuery("DELETE FROM People WHERE ID = '" + id + "'").executeUpdate();
		return people;
	}

	/*
	 * For retrieving persons with a common
	 * substring from the Table people
	 */
	@SuppressWarnings("unchecked")
	public List<People> search(String searchString) {
		return session.getCurrentSession().createQuery("FROM People WHERE FIRSTNAME LIKE '%" + searchString + "%' OR LASTNAME LIKE '%" + searchString + 
				"%' OR CITY LIKE '%" + searchString + "%' OR STATE LIKE '%" + searchString + "%' OR COUNTRY LIKE '%" + searchString + "%' OR PRIMARYEMAIL LIKE '%" 
				+ searchString + "%'").list();
	}
	
	/* 
	 * For retrieving all persons 
	 * from the Table people with 
	 * the given ids
	 */
	@SuppressWarnings("unchecked")
	public List<People> getListByIds(List<Integer> ids) {
		return (List<People>) session.getCurrentSession().createCriteria(People.class).add(Restrictions.in("id", ids)).list();
	}
	
	/* 
	 * For retrieving all person Ids 
	 * from the Table people
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllIds() {
		return (List<Integer>) session.getCurrentSession().createQuery("SELECT id FROM People").list();
	}
	
	 /* 
	 * For retrieving all persons from the Table people
	 */
	@SuppressWarnings("unchecked")
	public List<People> getAll() {
		return session.getCurrentSession().createQuery("FROM People").list();
	}
}
