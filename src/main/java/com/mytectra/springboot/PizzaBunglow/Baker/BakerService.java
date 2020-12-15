package com.mytectra.springboot.PizzaBunglow.Baker;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.Store.AddOnStore;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaStore;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.AddOnsRequest;
import com.mytectra.springboot.PizzaBunglow.model.OrderItem;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder.Status;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequests;

@Component
public class BakerService implements Baker{
	
	@Autowired
	private PizzaStore pizzaStore;
	
	@Autowired
	private AddOnStore addOnStore;
	
	
	@Override
	public PizzaOrder bake(PizzaRequests pizzaRequests, List<AddOnsRequest> addOnsList) throws PizzaBakeException, PizzaNotFoundException, AddOnsNotFoundException {
		PizzaOrder order = new PizzaOrder();
		order.setOrderId(new Random().nextInt());
		for(PizzaRequest request : pizzaRequests.getPizzaRequests() ) {
			Pizza pizza = pizzaStore.getPizzaByName(request.getPizzaName());
			if(pizza != null) {
			order.addOrder(new OrderItem(pizza, request.getCount()));
			} else {
				throw new PizzaBakeException("Sorry we cannot deliver - "+ request.getPizzaName() + " we dont have it right now" );
			}
		}
		
		for(AddOnsRequest request : addOnsList ) {
			AddOns addOns = addOnStore.getAddOnsByName(request.getName());
			if(addOns != null) {
			order.addOrder(new OrderItem(addOns, request.getCount()));
			} else {
				throw new PizzaBakeException("Sorry we cannot deliver - "+ request.getName() + " we dont have it right now" );
			}
		}
		
		if(order.getOrderItem()!=null && order.getOrderItem().size()>0) {
			for(OrderItem item: order.getOrderItem()) {
				item.setOrder(order);
			}
		}
		order.setStatus(Status.PROCESSING);
		order.setMessage("Thank You!");
		return order;
	}

	
	
}
