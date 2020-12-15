package com.mytectra.springboot.PizzaBunglow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Item {

	@Id
	@GeneratedValue
	//@Positive(message = "cannot be negative number")
	private int id;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Item(int id) {
		super();
		this.id = id;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
