package com.mytectra.springboot.PizzaBunglow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem {
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private int id;
	
	@OneToOne
	private Item item;
	 
	@Positive(message = "OrderItem count cannot be negative number")
	@Column(name="item_count")
	private int count;
	
	@ManyToOne
	@JsonIgnore
	private PizzaOrder order;
	
	public OrderItem() {
	}
	
	
	public OrderItem(Item item, @Positive(message = "OrderItem count cannot be negative number") int count) {
		super();
		this.item = item;
		this.count = count;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public PizzaOrder getOrder() {
		return order;
	}


	public void setOrder(PizzaOrder order) {
		this.order = order;
	}

	
	
	

}
