package com.mytectra.springboot.PizzaBunglow.PizzaKitchen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.Baker.Baker;
import com.mytectra.springboot.PizzaBunglow.Baker.PizzaBakeException;
import com.mytectra.springboot.PizzaBunglow.Billing.Billing;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.model.AddOnsRequest;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequests;

@Component
public class PizzaKitchenService implements PizzaKitchen{
	
	private List<PizzaOrder> orders= new ArrayList<>();

	@Autowired
	private Baker bakerService;
	
	@Autowired
	private Billing billingService;
	
	@Override
	public PizzaOrder AddOrder(PizzaRequests pizzaRequests, List<AddOnsRequest> addOnsList, String phoneNumber, Date orderDate) throws PizzaNotFoundException, AddOnsNotFoundException 	 
	{
		PizzaOrder order= new PizzaOrder();
		try {
			order = bakerService.bake(pizzaRequests, addOnsList);
			billingService.bill(order);
			order.setPhoneNumber(phoneNumber);
			order.setOrderDate(orderDate);
			orders.add(order);
		} catch (PizzaBakeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return order;
	}

	public List<PizzaOrder> getAllOrders(){
		return orders;
	}
	
	public List<PizzaOrder> getOrderByPhoneNumber(String phone) {
		
		List<PizzaOrder> selectOrders= new ArrayList<>();
		
		if(phone!=null && !phone.trim().isEmpty()) {
			for(PizzaOrder order : orders) {
				if(order.getPhoneNumber().equalsIgnoreCase(phone)) {
					selectOrders.add(order);
				}
			}
			}
		return selectOrders;
	}
	
	public PizzaOrder getOrderById(int id) {
		if(id>0) {
			for(PizzaOrder order : orders) {
				if(order.getOrderId()==id) {
					return order;
				}
			}
		}
		return null;
	}
	
	public boolean updateOrder(PizzaOrder order) {
		
		if(order.getOrderId()>0) {
			PizzaOrder order1= getOrderById(order.getOrderId());
			if(order1!=null) {
				orders.remove(order1);
				orders.add(order);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteOrderById(int id) {
		
		if(id>0) {
			PizzaOrder order= getOrderById(id);
			if(order!=null) {
				orders.remove(order);
				return true;
			}
		}
		return false;
	}
}
