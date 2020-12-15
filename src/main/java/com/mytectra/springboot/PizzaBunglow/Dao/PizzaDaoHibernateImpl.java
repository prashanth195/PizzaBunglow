package com.mytectra.springboot.PizzaBunglow.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;



//
@Transactional
@Component
@Primary
public class PizzaDaoHibernateImpl implements PizzaDao{
	
	@Autowired
	EntityManager manager;

	@Override
	public void save(Pizza pizza) {
		// TODO Auto-generated method stub
		manager.persist(pizza);
	}

	@Override
	public List<Pizza> getAllPizzas() {
		
		Query query = manager.createQuery("FROM Pizza");
		return query.getResultList();
	}

	@Override
	public Pizza getPizzaByName(String pizzaName) {

		Query query = manager.createNamedQuery("getPizzaByName");
		query.setParameter(1, pizzaName);
		return (Pizza) query.getSingleResult();
	}

	@Override
	public Pizza getPizzaById(int pizza_id) {
		 
		return manager.find(Pizza.class, pizza_id);
		
	}

	@Override
	public void deletePizzaById(int pizza_id) {
		// TODO Auto-generated method stub
		manager.remove(manager.find(Pizza.class, pizza_id));
	}

	
	
}
