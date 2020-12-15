package com.mytectra.springboot.PizzaBunglow.Baker;

import java.util.Date;
import java.util.List;

import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.model.AddOnsRequest;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequests;

public interface Baker {
	
	public PizzaOrder bake(PizzaRequests pizzaRequests, List<AddOnsRequest> addOnsList) throws PizzaBakeException, PizzaNotFoundException, AddOnsNotFoundException;

}
