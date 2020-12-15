package com.mytectra.springboot.PizzaBunglow.Dao;

import java.util.List;

import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;


public interface OrderDao {
	
	void save(PizzaOrder order);
	
	List<PizzaOrder> getAllOrders();
	
	List<PizzaOrder> getOrderByPhone(String phone);
	
	PizzaOrder getOrderById(int order_id);
	
	void deleteOrder(PizzaOrder order);
	
	void updateOrder(PizzaOrder order);

}
