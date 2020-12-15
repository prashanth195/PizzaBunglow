package com.mytectra.springboot.PizzaBunglow.PizzaKitchen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.Baker.Baker;
import com.mytectra.springboot.PizzaBunglow.Baker.PizzaBakeException;
import com.mytectra.springboot.PizzaBunglow.Billing.Billing;
import com.mytectra.springboot.PizzaBunglow.Dao.OrderDao;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.model.AddOnsRequest;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequests;

@Component
@Primary
public class PizzaKitchenDaoService implements PizzaKitchen{
	
	private List<PizzaOrder> orders= new ArrayList<>();
	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private Baker bakerService;
	
	@Autowired
	private Billing billingService;
	
	@Override
	public PizzaOrder AddOrder(PizzaRequests pizzaRequests, List<AddOnsRequest> addOnsList, String phoneNumber, Date orderDate) throws PizzaNotFoundException, AddOnsNotFoundException, PizzaBakeException 	 
	{
		PizzaOrder order= new PizzaOrder();
		
			order = bakerService.bake(pizzaRequests, addOnsList);
			billingService.bill(order);
			order.setPhoneNumber(phoneNumber);
			order.setOrderDate(orderDate);
			orders.add(order);
		
		orderDao.save(order);
		return order;
	}

	public List<PizzaOrder> getAllOrders(){
		return orderDao.getAllOrders();
	}
	
	public List<PizzaOrder> getOrderByPhoneNumber(String phone) {
		
		return orderDao.getOrderByPhone(phone);
	}
	
	public PizzaOrder getOrderById(int id) {
		if(id>0) {
			return orderDao.getOrderById(id);
		}
		return null;
	}
	
	public boolean updateOrder(PizzaOrder order) {
		
		if(order.getOrderId()>0) {
			PizzaOrder order1= getOrderById(order.getOrderId());
			if(order1!=null) {
				order.setOrderId(order1.getOrderId());
				orderDao.updateOrder(order);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteOrderById(int id) {
		
		if(id>0) {
			PizzaOrder order= getOrderById(id);
			if(order!=null) {
				orderDao.deleteOrder(order);
				return true;
			}
		}
		return false;
	}
	
	
}
