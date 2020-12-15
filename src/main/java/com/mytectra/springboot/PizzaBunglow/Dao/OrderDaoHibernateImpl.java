package com.mytectra.springboot.PizzaBunglow.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;

@Component
@Transactional
public class OrderDaoHibernateImpl implements OrderDao{

	@Autowired
	EntityManager manager;
	
	@Override
	public void save(PizzaOrder order) {
		
		manager.persist(order);
	}

	@Override
	public List<PizzaOrder> getAllOrders() {
		
		Query query = manager.createQuery("FROM PizzaOrder");
		return query.getResultList();
	}

	@Override
	public List<PizzaOrder> getOrderByPhone(String phone) {
		// TODO Auto-generated method stub
		Query query=manager.createNamedQuery("getPizzaOrdersByPhone");
		query.setParameter(1, phone);
		return query.getResultList();
	}

	@Override
	public PizzaOrder getOrderById(int order_id) {
		// TODO Auto-generated method stub
		return manager.find(PizzaOrder.class, order_id);
	}

	@Override
	public void deleteOrder(PizzaOrder order) {
		
		manager.remove(order);
	}

	@Override
	public void updateOrder(PizzaOrder order) {
		
		manager.merge(order);
	}


}
