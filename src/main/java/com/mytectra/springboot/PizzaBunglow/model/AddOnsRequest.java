package com.mytectra.springboot.PizzaBunglow.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.mytectra.springboot.PizzaBunglow.constraint.ValidAddons;

//import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest.Base;
//import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest.Size;

public class AddOnsRequest {
	
	@NotEmpty(message = "AddOns Name Cannot be empty or null")	
	@Size(min = 3 , max = 50 , message = "Size to be between 3 - 50 length")
	@NotBlank(message ="Addons Name Cannot be white spaces")
	//@ValidAddons
	private String name;
	
	@Positive(message = "cannot be negative number")
	private int count;
	
	public AddOnsRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
