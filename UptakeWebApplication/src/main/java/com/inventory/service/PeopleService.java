package com.inventory.service;

import java.util.List;

import com.inventory.bean.PeopleBean;
import com.inventory.model.People;

/**
 * @author vaibhav malhotra
 * 
 * Interface for the
 * Service Class for the
 * People DAO Class
 */

public interface PeopleService {
	public People add(People people);
	public List<People> addAll(List<People> peoples);
	public People get(Integer id);
	public People update(PeopleBean people, Integer editBeanId);
	public People delete(Integer id);
	public List<People> search(String searchString);
	public List<People> getListByIds(List<Integer> ids);
	public List<Integer> getAllIds();
	public List<People> getAll();
}
