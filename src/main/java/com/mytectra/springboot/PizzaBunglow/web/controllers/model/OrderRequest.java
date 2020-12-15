package com.mytectra.springboot.PizzaBunglow.web.controllers.model;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mytectra.springboot.PizzaBunglow.model.AddOnsRequest;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequests;

@Validated
public class OrderRequest {

	@NotNull 
	@Valid
	private PizzaRequests pizzaRequests;
	
	@NotNull
	@Valid
	private List<AddOnsRequest> addOnsRequests;
	
	@Pattern(regexp = "[0-9]{10}" , message = "invalid phone number")
	private String phoneNumber;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	@FutureOrPresent
	private Date orderDate;
	
	public OrderRequest() {
		
	}
	
	public OrderRequest(@NotNull @NotBlank PizzaRequests pizzaRequests,
			@NotNull @NotBlank List<AddOnsRequest> addOnsRequests,
			@Pattern(regexp = "[0-9]{10}", message = "invalid phone number") String phoneNumber,
			@FutureOrPresent Date orderDate) {
		super();
		this.pizzaRequests = pizzaRequests;
		this.addOnsRequests = addOnsRequests;
		this.phoneNumber = phoneNumber;
		this.orderDate = orderDate;
	}

	public PizzaRequests getPizzaRequests() {
		return pizzaRequests;
	}

	public void setPizzaRequests(PizzaRequests pizzaRequests) {
		this.pizzaRequests = pizzaRequests;
	}

	public List<AddOnsRequest> getAddOnsRequests() {
		return addOnsRequests;
	}

	public void setAddOnsRequests(List<AddOnsRequest> addOnsRequests) {
		this.addOnsRequests = addOnsRequests;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	
}
