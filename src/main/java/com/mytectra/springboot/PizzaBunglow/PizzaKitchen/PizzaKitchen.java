package com.mytectra.springboot.PizzaBunglow.PizzaKitchen;

import java.util.Date;
import java.util.List;

import com.mytectra.springboot.PizzaBunglow.Baker.PizzaBakeException;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.model.AddOnsRequest;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequests;

public interface PizzaKitchen {
	
	public PizzaOrder AddOrder(PizzaRequests pizzaRequest,List<AddOnsRequest> addOnsList, String phoneNumber, Date orderDate) throws PizzaNotFoundException, AddOnsNotFoundException, PizzaBakeException;

	public List<PizzaOrder> getAllOrders();
	
	public List<PizzaOrder> getOrderByPhoneNumber(String phone);
	
	public PizzaOrder getOrderById(int id);
	
	public boolean updateOrder(PizzaOrder order);
	
	public boolean deleteOrderById(int id);
}
