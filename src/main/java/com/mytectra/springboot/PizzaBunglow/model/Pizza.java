package com.mytectra.springboot.PizzaBunglow.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonRootName("pizza")
//@Document(collection = "pizzastore")
@Entity
@Table(name = "pizza")
@NamedQuery(name = "getPizzaByName" , query = "FROM Pizza p where p.name = ?1")
public class Pizza extends Item{
	
	/*@JsonProperty("pizza_id" )
	@Positive(message = "cannot be negative number")
	@Id
	@Column(name="id")
	private int id;*/
		
	
	@Column(name="name" )
	@NotEmpty(message = "Pizza Name Cannot be empty or null")	
	@Size(min = 3 , max = 50 , message = "Size to be between 3 - 50 length")
	@NotBlank(message ="Pizza Name Cannot be white spaces")
	private String name;
	
	@NotEmpty(message = "Pizza Description Cannot be empty or null")	
	@NotBlank(message ="Pizza Description Cannot be white spaces")
	@Column(name="description")
	private String description;
	
	@Positive(message = "cannot be negative number")
	@Column(name="cost")
	private int cost;
	
	public Pizza() {}

	public Pizza(int id,String name, String description, int cost) {
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

