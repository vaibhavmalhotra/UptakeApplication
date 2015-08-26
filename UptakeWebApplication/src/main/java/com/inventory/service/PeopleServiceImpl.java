package com.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.bean.PeopleBean;
import com.inventory.dao.PeopleDao;
import com.inventory.model.People;

/**
 * @author vaibhav malhotra
 * 
 * Service Class for the
 * People DAO Class
 *
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PeopleServiceImpl implements PeopleService {

	/**
	 * PeopleDao variable for
	 * accessing the DAO
	 * of People Model Class
	 */
	@Autowired
	private PeopleDao peopleDao;
	
	/* 
	 * Service method for invoking the add DAO method
	 */
	public People add(People people) {
		return peopleDao.add(people);
	}

	/* 
	 * Service method for invoking the addAll DAO method
	 */
	public List<People> addAll(List<People> peoples) {
		return peopleDao.addAll(peoples);
	}
	
	/* 
	 * Service method for invoking the get DAO method
	 */
	public People get(Integer id) {
		return peopleDao.get(id);
	}
	
	/* 
	 * Service method for invoking the update DAO method
	 */
	public People update(PeopleBean people, Integer editBeanId) {
		return peopleDao.update(people, editBeanId);
	}

	/* 
	 * Service method for invoking the delete DAO method
	 */
	public People delete(Integer id) {
		return peopleDao.delete(id);
	}

	/* 
	 * Service method for invoking the search DAO method
	 */
	public List<People> search(String searchString) {
		return peopleDao.search(searchString);
	}
	
	/* 
	 * Service method for invoking the getListByIds DAO method
	 */
	public List<People> getListByIds(List<Integer> ids) {
		return peopleDao.getListByIds(ids);
	}
	
	/* 
	 * Service method for invoking the getAllIds DAO method
	 */
	public List<Integer> getAllIds() {
		return peopleDao.getAllIds();
	}
	
	/* 
	 * Service method for invoking the getAll DAO method
	 */
	public List<People> getAll() {
		return peopleDao.getAll();
	}

}
