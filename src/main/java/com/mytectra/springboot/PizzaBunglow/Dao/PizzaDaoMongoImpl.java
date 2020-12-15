package com.mytectra.springboot.PizzaBunglow.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;

//@Component
//@Primary
public class PizzaDaoMongoImpl implements PizzaDao{
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void save(Pizza pizza) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(pizza);
	}

	@Override
	public List<Pizza> getAllPizzas() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Pizza.class);
	}

	@Override
	public Pizza getPizzaByName(String pizzaName) {
		// TODO Auto-generated method stub
		return mongoTemplate.findOne(Query.query(Criteria.where("name").in(pizzaName)), Pizza.class);
	}

	@Override
	public Pizza getPizzaById(int pizza_id) {
		// TODO Auto-generated method stub
		//return mongoTemplate.findOne(Query.query(Criteria.where("_id").in(pizza_id)), Pizza.class);
		return mongoTemplate.findById(pizza_id, Pizza.class);
	}

	@Override
	public void deletePizzaById(int pizza_id) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(Query.query(Criteria.where("_id").in(pizza_id)), Pizza.class);
	}

	
	
}
