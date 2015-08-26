package com.inventory.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inventory.bean.PeopleBean;
import com.inventory.model.People;
import com.inventory.service.PeopleService;

/**
 * @author vaibhav malhotra
 * 
 * Controller Class for the
 * Spring Rest Web Service.
 */

@Controller
public class PeopleRestController extends PeopleController {

	/**
	 * PeopleService variable for
	 * accessing and manipulating
	 * the People Model Class
	 */
	@Autowired
	 private PeopleService peopleService;
	 	
	/**
	 * Map variable to 
	 * group and maintain
	 * People into Families
	 */
	 private Map<String, List<PeopleBean>> families = new HashMap<String, List<PeopleBean>>();
	
	 /**
	 * @return
	 * 
	 * For listing all persons
	 * from the Table people
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	 public List<PeopleBean> listPeoples() {
		  return prepareListofBean(peopleService.getAll());
	 }

	/**
	 * @param peopleBean
	 * @return
	 * 
	 * For adding a new person
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	 public ResponseEntity addPeople(@RequestBody PeopleBean peopleBean) {
		String error = checkErrors(peopleBean);
		  if(error != null) {
			  return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		  }
		return new ResponseEntity(peopleService.add(prepareModel(peopleBean)), HttpStatus.CREATED) ;
	 }
	
	/**
	 * @return
	 * 
	 * For getting a person
	 * from the Table people
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	 public People getPeople(@PathVariable("id") Integer id) {
		  return peopleService.get(id);
	 }
	
	/**
	 * @param peopleBean
	 * @return
	 * 
	 * For updating an existing person
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity updatePeople(@PathVariable("id") Integer id, 
			@RequestBody PeopleBean peopleBean) {
		String error = checkUpdateErrors(peopleBean);
		if(error != null) {
			return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(peopleService.update(peopleBean, id), HttpStatus.OK);
	 }

	/**
	 * @param peopleBean
	 * @return
	 * 
	 * For deleting an existing person
	 */

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public People deletePeople(@PathVariable("id") Integer id) {
	     return peopleService.delete(id);
	 }

	/**
	 * @param searchString
	 * @return
	 * 
	 * For searching persons based
	 * on a substring
	 */

	@RequestMapping(value = "/search/{searchString}", method = RequestMethod.GET)
	@ResponseBody
	public List<PeopleBean> searchPeoples(@PathVariable("searchString") String searchString) {
		  return prepareListofBean(peopleService.search(searchString));
	 }

	/**
	 * @param familyName
	 * @param ids
	 * @return
	 * 
	 * For creating new Family
	 * from existing people in table
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createFamilyFromExistingPeople/{familyName}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity createFamilyFromExistingPeople(@PathVariable("familyName") String familyName,
			@RequestBody List<Integer> ids) {
		List<Integer> peopleIds = peopleService.getAllIds();
		for(Integer id : ids) {
			if(!peopleIds.contains(id)) {
				return new ResponseEntity("Person ID '" + id + "' does not exist!", HttpStatus.BAD_REQUEST);
			}
		}
	
		if(families.containsKey(familyName)) {
			return new ResponseEntity("'" + familyName + "' family already exists!", HttpStatus.BAD_REQUEST);
		}
		List<People> peoples = peopleService.getListByIds(ids);
		families.put(familyName, prepareListofBean(peoples));
		return new ResponseEntity("'" + familyName + "' family created successfully!", HttpStatus.CREATED);
	 }

	/**
	 * @param familyName
	 * @param peoples
	 * @return
	 * 
	 * For creating new Family
	 * from new people
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createFamilyFromNewPeople/{familyName}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity createFamilyFromNewPeople(@PathVariable("familyName") String familyName,
			@RequestBody List<Object> objects) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonInput = mapper.valueToTree(objects);
		List<PeopleBean> peopleBeans = mapper.readValue(jsonInput, new TypeReference<List<PeopleBean>>(){});
		for (PeopleBean peopleBean : peopleBeans) {
			String error = checkErrors(peopleBean);
			  if(error != null) {
				  return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
			  }
		}
		
		if(families.containsKey(familyName)) {
			return new ResponseEntity("'" + familyName + "' family already exists!", HttpStatus.BAD_REQUEST);
		}
		List<People> peoples = peopleService.addAll(prepareModelAll(peopleBeans));
		families.put(familyName, prepareListofBean(peoples));
		return new ResponseEntity("'" + familyName + "' family created successfully!", HttpStatus.CREATED);
	 }
	
	/**
	 * @param familyName
	 * @param ids
	 * @return
	 * 
	 * For adding existing people
	 * To a Family
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addExistingPeopleToFamily/{familyName}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity addExistingPeopleToFamily(@PathVariable("familyName") String familyName,
			@RequestBody List<Integer> ids) {
		if(!families.containsKey(familyName)) {
			return new ResponseEntity("'" + familyName + "' family does not exist!", HttpStatus.BAD_REQUEST);
		}
		
		List<Integer> peopleIds = peopleService.getAllIds();
		for(Integer id : ids) {
			if(!peopleIds.contains(id)) {
				return new ResponseEntity("Person ID '" + id + "' does not exist!", HttpStatus.BAD_REQUEST);
			}
		}
	
		List<People> peoples = peopleService.getListByIds(ids);
		List<PeopleBean> list = families.get(familyName);
		list.addAll(prepareListofBean(peoples));
		return new ResponseEntity("'" + familyName + "' family updated successfully!", HttpStatus.OK);
	 }

	/**
	 * @param familyName
	 * @param peoples
	 * @return
	 * 
	 * For adding new people
	 * To a Family
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addNewPeopleToFamily/{familyName}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity addNewPeopleToFamily(@PathVariable("familyName") String familyName,
			@RequestBody List<Object> objects) throws JsonParseException, JsonMappingException, IOException {
		if(!families.containsKey(familyName)) {
			return new ResponseEntity("'" + familyName + "' family does not exist!", HttpStatus.BAD_REQUEST);
		}
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonInput = mapper.valueToTree(objects);
		List<PeopleBean> peopleBeans = mapper.readValue(jsonInput, new TypeReference<List<PeopleBean>>(){});
		for (PeopleBean peopleBean : peopleBeans) {
			String error = checkErrors(peopleBean);
			  if(error != null) {
				  return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
			  }
		}
		
		List<People> peoples = peopleService.addAll(prepareModelAll(peopleBeans));
		List<PeopleBean> list = families.get(familyName);
		list.addAll(prepareListofBean(peoples));
		return new ResponseEntity("'" + familyName + "' family updated successfully!", HttpStatus.OK);
	 }


	/**
	 * @param familyName
	 * @return
	 * 
	 * For retrieving people
	 * Of a Family
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getPeopleFromFamily/{familyName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getPeopleFromFamily(@PathVariable("familyName") String familyName) {
		if(!families.containsKey(familyName)) {
			return new ResponseEntity("'" + familyName + "' family does not exist!", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(families.get(familyName), HttpStatus.OK);
	 }

}
