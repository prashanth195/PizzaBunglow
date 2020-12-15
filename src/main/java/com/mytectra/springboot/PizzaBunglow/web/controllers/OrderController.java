package com.mytectra.springboot.PizzaBunglow.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.OrderRequest;
import com.mytectra.springboot.PizzaBunglow.Baker.PizzaBakeException;
import com.mytectra.springboot.PizzaBunglow.PizzaKitchen.PizzaKitchen;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.ResponseWrapper;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.ResponseWrapper.Status;

@Validated
@RestController
public class OrderController {

	@Autowired
	private PizzaKitchen pizzaKitchen;
	
	@GetMapping(path="/orders", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
 	public @ResponseBody List<PizzaOrder> getAllPizzaOrder(){
		return pizzaKitchen.getAllOrders();
 	}
	
	@GetMapping(path="/orders/search", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
 	public List<PizzaOrder> getPizzaOrder(@RequestParam (name="Phone") @NotNull String phone){
		
		return pizzaKitchen.getOrderByPhoneNumber(phone);
 	}
	
	@GetMapping(path="/orders/{id}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public PizzaOrder getPizzaOrder(@PathVariable("id") Integer id, @RequestHeader(name = "client" ,required = false) String client) {
		System.out.println("Client is "+client);
		return pizzaKitchen.getOrderById(id);
	}
	
	@PostMapping(path="/orders", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public PizzaOrder addOrders(@RequestBody @Valid OrderRequest request) throws Exception {
		
		return pizzaKitchen.AddOrder(request.getPizzaRequests(), request.getAddOnsRequests(), request.getPhoneNumber(), request.getOrderDate());
		//return new ResponseWrapper<String>("Successfully Added", Status.SUCCESS);
	}
	
	@PutMapping(path="/orders/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseWrapper<?> updateOrder(@PathVariable("id") Integer id, @RequestBody PizzaOrder order, 
			@RequestHeader(name = "clientName" ,required = false) String client,
			@RequestHeader(name = "clientId" ,required = false) String clientId) {
		order.setOrderId(id);
		
		if(id>0 && pizzaKitchen.updateOrder(order)) {
			return new ResponseWrapper<String>("Successfully Updated", Status.SUCCESS);
		}else {
			return new ResponseWrapper<String>("Failed to Update", Status.FAILURE);
		}
	}
	
	@DeleteMapping(path="/orders/{id}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseWrapper<?> deleteOrder(@PathVariable("id") Integer id) {
		if(id>0 && pizzaKitchen.deleteOrderById(id)) {
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
	
	@ExceptionHandler(PizzaNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?>  handlePizzaNotFoundEx(PizzaNotFoundException obj) {
		
	
		List<Error> errs = new ArrayList<Error>();
	

		Error errS = new Error("Pizza Unavailable","Requested Pizza is not available");
		errs.add(errS);

	
	return  new ResponseWrapper<List<Error> >(errs, Status.FAILURE);
	}
	
	@ExceptionHandler(PizzaBakeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?>  handlePizzaBakeEx(PizzaBakeException obj) {
		
	
		List<Error> errs = new ArrayList<Error>();
	

		Error errS = new Error("Pizza Not Deliverable",obj.getMessage());
		errs.add(errS);

	
	return  new ResponseWrapper<List<Error> >(errs, Status.FAILURE);
	}
}
