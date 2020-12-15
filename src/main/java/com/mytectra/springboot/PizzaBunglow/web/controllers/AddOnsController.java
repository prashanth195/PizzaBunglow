package com.mytectra.springboot.PizzaBunglow.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytectra.springboot.PizzaBunglow.web.controllers.model.Error;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnStore;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.ResponseWrapper;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.ResponseWrapper.Status;


@Validated
@RestController
public class AddOnsController {

	@Autowired
	private AddOnStore addOnsStore;
	
	@PreAuthorize(value = "hasRole('ROLE_USER')")
	@Secured("ROLE_USER")
	@RolesAllowed("ROLE_USER")
	@GetMapping(path="/addOns", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
 	public @ResponseBody List<AddOns> getAddOns(){
		return addOnsStore.getAllAddOns();
 	}
	
	@GetMapping(path="/addOns/search", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
 	public AddOns getAddOn(@RequestParam (name="name") @NotNull String name) throws AddOnsNotFoundException{
		AddOns addOn= addOnsStore.getAddOnsByName(name);
		return addOn;
 	}
	
	@GetMapping(path="/addOns/{id}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public AddOns getAddOns(@PathVariable("id") Integer id, @RequestHeader(name = "client" ,required = false) String client) throws AddOnsNotFoundException {
		System.out.println("Client is "+client);
		return addOnsStore.getAddOnsById(id);
	}
	
	@PostMapping(path="/addOns", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseWrapper<?> AddaddOns(@RequestBody AddOns addOns) {
		addOnsStore.addAddOns(addOns);
		return new ResponseWrapper<String>("Successfully Added", Status.SUCCESS);
	}
	
	@PutMapping(path="/addOns/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseWrapper<?> updateAddOns(@PathVariable("id") Integer id, @RequestBody AddOns addOns, 
			@RequestHeader(name = "clientName" ,required = false) String client,
			@RequestHeader(name = "clientId" ,required = false) String clientId) throws AddOnsNotFoundException {
		addOns.setId(id);
		
		if(addOnsStore.updateAddOnsById(addOns)) {
			return new ResponseWrapper<String>("Successfully Updated", Status.SUCCESS);
		}else {
			return new ResponseWrapper<String>("Failed to Update", Status.FAILURE);
		}
	}
	
	@DeleteMapping(path="/addOns/{id}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseWrapper<?> deleteAddOns(@PathVariable("id") Integer id) throws AddOnsNotFoundException {
		if(addOnsStore.deleteAddOnsById(id)) {
			return new ResponseWrapper<String>("Successfully Deleted", Status.SUCCESS);
		}else {
			return new ResponseWrapper<String>("Failed to delete", Status.FAILURE);
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?> handleConstraintEx(ConstraintViolationException obj){
		Set<ConstraintViolation<?>> results = obj.getConstraintViolations();
		List<Error> errs= new ArrayList<>();
		for(ConstraintViolation err: results) {
			String errMsg= String.format(" %s Field is failed due to %s , value given is %s", err.getPropertyPath().toString() , err.getMessage() , err.getInvalidValue());
			errs.add(new Error(err.getPropertyPath().toString(), errMsg));
		}
		
		return new ResponseWrapper<List<Error>>(errs, Status.FAILURE);
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?> handleNullPointerEx(NullPointerException obj){
		
			String errMsg= String.format(" %s Field is failed due to %s , value given is NULL", obj.getCause() , obj.getMessage() );
			Error err=new Error(obj.getLocalizedMessage(), errMsg);		
		
		return new ResponseWrapper<Error>(err, Status.FAILURE);
	}
	
	@ExceptionHandler(AddOnsNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?> handleAddOnsNotFoundEx(AddOnsNotFoundException obj){
		
			
			Error err=new Error("AddOns Not Available", "Failed to find the AddOns");		
		
		return new ResponseWrapper<Error>(err, Status.FAILURE);
	}
}
