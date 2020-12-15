package com.mytectra.springboot.PizzaBunglow.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PizzaRequests {
	
	@Size(min = 1 , message = "No Pizza Requests added")
	@NotEmpty(message = "Pizza Requests Cannot be empty or null")
	@Valid
	private List<PizzaRequest> pizzaRequests;
	
	public PizzaRequests() {}

	public PizzaRequests(List<PizzaRequest> pizzaRequests) {
		super();
		this.pizzaRequests = pizzaRequests;
	}

	public List<PizzaRequest> getPizzaRequests() {
		return pizzaRequests;
	}

	public void setPizzaRequests(List<PizzaRequest> pizzaRequests) {
		this.pizzaRequests = pizzaRequests;
	}
	
	

}
