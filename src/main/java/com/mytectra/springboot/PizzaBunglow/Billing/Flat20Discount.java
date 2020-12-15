package com.mytectra.springboot.PizzaBunglow.Billing;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.OrderItem;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;

@Component
@ConditionalOnProperty(name="pizza.Flat20", havingValue="true")
public class Flat20Discount implements Discount {



	@Override
	public double discount(PizzaOrder order) {
		if(order == null) {
			throw new  IllegalArgumentException("Order cannot be null");
		}
		
		double discount=0;
			
			for(OrderItem orderItem: order.getOrderItem()) {
				if(orderItem.getItem()!=null && orderItem.getItem() instanceof Pizza) {
					Pizza pizza= (Pizza) orderItem.getItem();
					
					if(pizza.getName().equalsIgnoreCase("Panner Pizza")) 
			{	
			 discount = discount+pizza.getCost() * 0.2 * orderItem.getCount();
			}
				}
			
				if(orderItem.getItem()!=null && orderItem.getItem() instanceof AddOns) {
					AddOns addons=(AddOns) orderItem.getItem();
					if(addons.getName().equalsIgnoreCase("Fries")) {	
			 discount = discount+addons.getCost() * 0.2 * orderItem.getCount();
			}
			}
			}
			return discount;
	}

}
