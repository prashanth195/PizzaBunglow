package com.mytectra.springboot.PizzaBunglow.Billing;

import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;

public interface Discount {
	
	double discount(PizzaOrder order);

}
