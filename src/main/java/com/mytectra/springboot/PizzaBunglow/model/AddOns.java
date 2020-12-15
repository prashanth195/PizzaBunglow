package com.mytectra.springboot.PizzaBunglow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("addons")
@Entity
@Table(name="addons")
@NamedQuery(name = "getAddOnsByName" , query = "FROM AddOns a where a.name = ?1")
public class AddOns extends Item{
	
	/*@JsonProperty("addons_id")
	@Positive(message = "cannot be negative number")
	@Column(name="id")
	@Id
	private int id;*/
	
	@NotEmpty(message = "AddOns Name Cannot be empty or null")	
	@Size(min = 3 , max = 50 , message = "Size to be between 3 - 50 length")
	@NotBlank(message ="Addons Name Cannot be white spaces")
	@Column(name="name")
	private String name;
	
	@NotEmpty(message = "Addons description Cannot be empty or null")	
	@Size(min = 3 , max = 50 , message = "Size to be between 3 - 50 length")
	@NotBlank(message ="Addons description Cannot be white spaces")
	@Column(name="description")
	private String description;
	
	@Positive(message = "cannot be negative number")
	@Column(name="cost")
	private int cost;
	
	public AddOns() {		}
	
	

	public AddOns(int id, String name, String description, int cost) {
		super(id);
		
		this.name = name;
		this.description = description;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	
}
