package com.mytectra.springboot.PizzaBunglow.Billing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.OrderItem;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;

@Component
public class BuyOneGetOneDiscount implements Discount{

	@Override
	public double discount(PizzaOrder order) {
		// TODO Auto-generated method stub
		
		for(OrderItem orderItem: order.getOrderItem()) {
			if(orderItem.getItem()!=null && orderItem.getItem() instanceof Pizza) {
				Pizza pizza= (Pizza) orderItem.getItem();
				
			if(pizza.getName().equalsIgnoreCase("Panner Pizza")) 
			{
				
				int count= orderItem.getCount();
				for(int i=1; i<=count;i++) {
					orderItem.setCount(orderItem.getCount()+1);
				}
			}
			
			}
			
			if(orderItem.getItem()!=null && orderItem.getItem() instanceof AddOns) {
				AddOns addons=(AddOns) orderItem.getItem();
			if(addons.getName().equalsIgnoreCase("Fries")) 
			{
				
				int count= orderItem.getCount();
				for(int i=1; i<=count;i++) {
					orderItem.setCount(orderItem.getCount()+1);
				}
			}
			}
			}
		
		
		return 0;
	}

}
