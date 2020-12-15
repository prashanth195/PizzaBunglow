package com.mytectra.springboot.PizzaBunglow.Dao;

import java.util.List;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;

public interface PizzaDao {
	
	void save(Pizza pizza);
	
	List<Pizza> getAllPizzas();

	Pizza getPizzaByName(String pizzaName);
	
	Pizza getPizzaById(int pizza_id);
	
	void deletePizzaById(int pizza_id);
}
