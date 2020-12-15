package com.mytectra.springboot.PizzaBunglow.Store;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;

public interface PizzaStore {
	
	public void addPizza(Pizza pizza);
	
	public void addPizzaList(List<Pizza> pizzaList) ;
	
	
	//ALways limit to top 10
	public List<Pizza> getAllPizzas();
	
	public List<Pizza> getAllPizzas(int page);
	
	
	//throw PNFE , 
	public Pizza getPizzaByName(String pizzaName) throws PizzaNotFoundException;
	
	//throw PNFE
	public Pizza getPizzaById(int id) throws PizzaNotFoundException;
	
	//throw PNFE
	public void updatePizza(Pizza pizza) throws PizzaNotFoundException;
	
	
	public void deletePizza(int id) throws PizzaNotFoundException;

}
