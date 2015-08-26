package com.inventory.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inventory.bean.PeopleBean;
import com.inventory.service.PeopleService;

/**
 * @author vaibhav malhotra
 * 
 * Controller Class for the Spring MVC
 * Web Application. Controls all the
 * interactions between the Service Layer
 * and Views
 */
@Controller
public class PeopleMVCController extends PeopleController {
 
 /**
 * PeopleService variable for
 * accessing and manipulating
 * the People Model Class
 */
@Autowired
 private PeopleService peopleService;
 
 /**
 * editBeanId variable for holding
 * the Id of person to be edited
 */
private Integer editBeanId;
 
/**
 * @param peopleBean
 * @return
 * 
 * For saving a new person or 
 * editing an existing person
 */

@RequestMapping(value = "/save", method = RequestMethod.POST)
public ModelAndView savePeople(@ModelAttribute("command")PeopleBean peopleBean) {
	  String error = checkErrors(peopleBean);
	  if(error != null) {
		  Map<String, Object> model = new HashMap<String, Object>();
		  model.put("error", error);
		  return new ModelAndView("addPeople", model);
	  }
	  
	  if(editBeanId != null) {
		  peopleService.update(peopleBean, editBeanId);
	  } else {
		  peopleService.add(prepareModel(peopleBean));
	  }
	  
	  editBeanId = null;
	  return new ModelAndView("redirect:/index");
 }

 /**
 * @return
 * 
 * For listing all persons
 * from the Table people
 */
@RequestMapping(value = "/index", method = RequestMethod.GET)
 public ModelAndView listPeoples() {
	  Map<String, Object> model = new HashMap<String, Object>();
	  model.put("peoples", prepareListofBean(peopleService.getAll()));
	  return new ModelAndView("peoplesList", model);
 }

 /**
 * @param peopleBean
 * @return
 * 
 * For adding a new person 
 * or editing an existing person
 */

@RequestMapping(value = "/add", method = RequestMethod.GET)
 public ModelAndView addPeople(@ModelAttribute("command")PeopleBean peopleBean) {
	 editBeanId = peopleBean.getId();
	 return new ModelAndView("addPeople");
 }

/**
 * @param peopleBean
 * @return
 * 
 * For deleting an existing person
 */

@RequestMapping(value = "/delete", method = RequestMethod.GET)
public ModelAndView deletePeople(@ModelAttribute("command")PeopleBean peopleBean) {
     peopleService.delete(peopleBean.getId());
     return new ModelAndView("redirect:/index");
 }

/**
 * @param searchString
 * @return
 * 
 * For searching persons based
 * on a substring
 */

@RequestMapping(value = "/search", method = RequestMethod.GET)
public ModelAndView searchPeoples(@RequestParam("searchString") String searchString) {
	  Map<String, Object> model = new HashMap<String, Object>();
	  model.put("peoples", prepareListofBean(peopleService.search(searchString)));
	  return new ModelAndView("peoplesList", model);
 }

}
