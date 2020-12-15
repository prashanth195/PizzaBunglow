package com.mytectra.springboot.PizzaBunglow.Billing;

import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.model.Price;

public interface Billing {
	
	public void bill(PizzaOrder order);

}
