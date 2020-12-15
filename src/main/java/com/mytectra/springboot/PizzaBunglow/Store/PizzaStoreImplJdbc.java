package com.mytectra.springboot.PizzaBunglow.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.mytectra.springboot.PizzaBunglow.Dao.PizzaDao;
import com.mytectra.springboot.PizzaBunglow.Dao.PizzaPageRepository;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.RequestScopeBean;

@Component
@Primary
public class PizzaStoreImplJdbc implements PizzaStore{
	
	private List<Pizza> pizzas= new ArrayList<Pizza>();
	
	@Autowired
	private PizzaDao dao;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private PizzaPageRepository pizzaPage;
	
	
	/*@Autowired
	private RequestScopeBean bean;*/

	@Override
	public void addPizza( @NotNull Pizza pizza) {
		Set<ConstraintViolation<Pizza>> result = validator.validate(pizza);
		if(result.isEmpty()) {
		//System.out.println("for " + bean.getClient());
		if(pizza!=null && 
				pizza.getName()!=null && !pizza.getName().trim().isEmpty() &&
				 pizza.getCost()!=0 && 
				pizza.getDescription()!=null && !pizza.getDescription().trim().isEmpty()){
		//pizzas.add(pizza);
			dao.save(pizza);
		}
		}
		/*else {
			throw new IllegalArgumentException();
		}*/
	//}
	}
	
	@Override
	public void addPizzaList( @NotNull List<Pizza> pizzaList) {
		
		
		//System.out.println("for " + bean.getClient());
		if(pizzaList!=null && pizzaList.size()>0) {
		for(Pizza pizza: pizzaList) {
		if(pizza!=null && 
				pizza.getName()!=null && !pizza.getName().trim().isEmpty() &&
				pizza.getId()!=0 && pizza.getCost()!=0 && 
				pizza.getDescription()!=null && !pizza.getDescription().trim().isEmpty()){
			//pizzas.add(pizza);
			dao.save(pizza);
		}
		}
		}
	}
	
	@Override
	public List<Pizza> getAllPizzas() {
		//System.out.println("for " + bean.getClient());

		//return pizzas;
		return dao.getAllPizzas();
	}

	@Override
	public Pizza getPizzaByName(String pizzaName) throws PizzaNotFoundException {
		//System.out.println("for " + bean.getClient());

		if(pizzaName!=null && !pizzaName.trim().isEmpty()) {
		/*for(Pizza pizza : pizzas) {
			if(pizza.getName().equalsIgnoreCase(pizzaName)) {
				return pizza;
			}
		}*/
			return dao.getPizzaByName(pizzaName);
		}
		throw new PizzaNotFoundException();
	}

	@Override
	public Pizza getPizzaById(int id) throws PizzaNotFoundException {
		// TODO Auto-generated method stub
		//System.out.println("for " + bean.getClient());

		if(id>0) {
			/*for(Pizza pizza : pizzas) {
				if(pizza.getId()==id) {
					return pizza;
				}
			}*/
			return dao.getPizzaById(id);
			}
		throw new PizzaNotFoundException();
	}

	@Override
	public void updatePizza(Pizza pizza) throws PizzaNotFoundException {
		// TODO Auto-generated method stub
		
		Pizza pizza1= getPizzaById(pizza.getId());
		if(pizza1!=null /*&& 
				pizza.getName()!=null && !pizza.getName().trim().isEmpty() &&
				pizza.getId()!=0 && pizza.getCost()!=0 && 
				pizza.getDescription()!=null && !pizza.getDescription().trim().isEmpty()*/){
		/*Pizza pizza1= getPizzaById(pizza.getId());
		if(pizza1!=null) {
			pizzas.remove(pizza1);
			pizzas.add(pizza);
		}*/
			
			dao.deletePizzaById(pizza1.getId());
			dao.save(pizza);
	}
			
	}

	@Override
	public void deletePizza(int id) throws PizzaNotFoundException {
		// TODO Auto-generated method stub
		if(id>0 /*&& 
				pizza.getName()!=null && !pizza.getName().trim().isEmpty() &&
				pizza.getId()!=0 && pizza.getCost()!=0 && 
				pizza.getDescription()!=null && !pizza.getDescription().trim().isEmpty()*/){
		/*Pizza pizza1= getPizzaById(id);
		if(pizza1!=null) {
			pizzas.remove(pizza1);
			
		}*/
			Pizza pizza1= getPizzaById(id);
			if(pizza1!=null)
			{
				dao.deletePizzaById(id);
			}
			
	}
			
	}

	@Override
	public List<Pizza> getAllPizzas(int page) {
		// TODO Auto-generated method stub
		page=page-1;
		Pageable PageWithTwoElements = PageRequest.of(page, 2, Sort.by("name"));
		List<Pizza> finalList= new ArrayList<>();
		Page<Pizza> pizzas= pizzaPage.findAll(PageWithTwoElements);
		
		for(Pizza p: pizzas) {
		finalList.add(p);
		}
		return finalList;
	}
}
