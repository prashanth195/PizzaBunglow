package com.mytectra.springboot.PizzaBunglow.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaStore;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.Error;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.RequestScopeBean;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.ResponseWrapper;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.ResponseWrapper.Status;
import com.mytectra.springboot.PizzaBunglow.web.validators.CustomValidator;

//@Controller
// @Validated is used for method leven validations
@Validated
@RestController
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaStore pizzaStore;
	
	@Autowired
	private RequestScopeBean bean;
	
	//@RequestMapping(path = "/pizzas" , method = RequestMethod.GET)
	/*
	 * Using HttpServlet Request 
	 * @GetMapping("/pizzas")
	public List<Pizza> getPizzas(HttpServletRequest request){
		System.out.println(request.getHeader("client"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getRequestURL());
		System.out.print(request.getMethod());
 bean.setClient(request.getHeader("client"));

		
		return pizzaStore.getAllPizzas();
	
	}*/
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public List<Pizza> getPizzas(@RequestHeader(name = "Page" ,required = false) int page){
		
		if(page==0) {
		return pizzaStore.getAllPizzas();
		}else if(page>0){
			return pizzaStore.getAllPizzas(page);
		}
		return null;
	}
	
	@GetMapping(path="/search", produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public Pizza getPizza(@RequestParam(required = true , name = "name") @Size(min = 10) String name) throws PizzaNotFoundException{
		Pizza pizza= pizzaStore.getPizzaByName(name);
		return pizza;
	
	}
	
	@PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE} )
	public ResponseWrapper<?> addPizza(@RequestBody Pizza pizza ) {
		pizzaStore.addPizza(pizza);
		return new ResponseWrapper<String>("Succesfully Created", Status.SUCCESS);
		
	}
	
/*	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?>  handleBindingEx(MethodArgumentNotValidException obj) {
		BindingResult results = obj.getBindingResult();
		List<FieldError> errors = results.getFieldErrors();
		List<Error> errs = new ArrayList<Error>();
		for(FieldError err : errors) {
			String errMsg = String.format("%s Field is failed due to %s , value given is %s", err.getField() , err.getDefaultMessage() , err.getRejectedValue());
			Error errS = new Error(err.getField(),errMsg);
			errs.add(errS);
		}
		
		return  new ResponseWrapper<List<Error> >(errs, Status.FAILURE);
	}*/
	

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?>  handleConstraintEx(ConstraintViolationException obj) {
		
		 Set<ConstraintViolation<?>> results = obj.getConstraintViolations();
		//List<FieldError> errors = results.getFieldErrors();
		List<Error> errs = new ArrayList<Error>();
	for(ConstraintViolation err : results) {
		String errMsg = String.format("%s Field is failed due to %s , value given is %s", err.getPropertyPath().toString() , err.getMessage() , err.getInvalidValue());
		Error errS = new Error(err.getPropertyPath().toString(),errMsg);
		errs.add(errS);
	}
	
	return  new ResponseWrapper<List<Error> >(errs, Status.FAILURE);
}
	
	@ExceptionHandler(PizzaNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?>  handlePizzaNotFoundEx(PizzaNotFoundException obj) {
		
		 //Set<ConstraintViolation<?>> results = obj.getConstraintViolations();
		//List<FieldError> errors = results.getFieldErrors();
		List<Error> errs = new ArrayList<Error>();
	//for(ConstraintViolation err : results) {
		//String errMsg = String.format("%s Field is failed due to %s , value given is %s", err.getPropertyPath().toString() , err.getMessage() , err.getInvalidValue());
		Error errS = new Error("Pizza Unavailable","Requested Pizza is not available");
		errs.add(errS);
	//}
	
	return  new ResponseWrapper<List<Error> >(errs, Status.FAILURE);
	}

	@PostMapping( headers = "List",consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public ResponseWrapper<?> addPizzaList(@Valid @RequestBody List<Pizza> pizzaList ) {
		pizzaStore.addPizzaList(pizzaList);
		return new ResponseWrapper<String>("Succesfully Created", Status.SUCCESS);
	}

	
	//Path Variable
	@GetMapping(path="/{id}", produces={MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public Pizza getPizza(@Valid @PathVariable("id") Integer id ,@Valid @RequestHeader(name = "client" ,required = false) String client) throws PizzaNotFoundException {
		System.out.println("THis is Header Param" + client);
		return pizzaStore.getPizzaById(id);
	}
	
	@PutMapping(path="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public ResponseWrapper<?> updatePizza(@Valid @PathVariable("id") Integer id ,@Valid @RequestBody Pizza pizza) throws PizzaNotFoundException {
		pizza.setId(id);
		pizzaStore.updatePizza(pizza);
		return new ResponseWrapper<String>("Succesfully Updated", Status.SUCCESS);
	}
	
	@DeleteMapping(path="/{id}", produces={MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public ResponseWrapper<?> deletePizza(@Valid @PathVariable("id") Integer id) throws PizzaNotFoundException {
		
		pizzaStore.deletePizza(id);
		return new ResponseWrapper<String>("Succesfully Deleted", Status.SUCCESS);
	}
	

}
