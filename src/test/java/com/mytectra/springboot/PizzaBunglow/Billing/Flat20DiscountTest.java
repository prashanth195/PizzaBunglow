
package com.mytectra.springboot.PizzaBunglow.Billing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.OrderItem;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;

@TestInstance(Lifecycle.PER_CLASS)
public class Flat20DiscountTest  {
	
	private Flat20Discount discount;
	
	private PizzaOrder order;
	

	@BeforeAll
	public void init2() {
		System.out.println("Before All Test");
		discount = new Flat20Discount();

	}
	
	@BeforeEach
	public void init() {
		System.out.println("Before Each Test");
		order = new PizzaOrder();
		
	}
	
	@Test
	public void testFlat20() {
		OrderItem item = new OrderItem(new Pizza(1, "x piza", "", 100), 2);
		OrderItem item1 = new OrderItem(new AddOns(1, "x addOns", "", 50), 2);
		order.addOrder(item);
		order.addOrder(item1);
		double dis = discount.discount(order);
		assertEquals(0, dis);
	}
	
	@Test
	public void testFlat20_nopizza () {
		OrderItem item = new OrderItem();
		order.addOrder(item);
		double dis = discount.discount(order);
		assertEquals(0, dis);
	}

	@Test
	public void testFlat20_nopanner () {
		OrderItem item = new OrderItem(new Pizza(1, "Panner Pizza", "", 100),2);
		OrderItem item1 = new OrderItem(new AddOns(1, "Fries", "", 50), 2);
		order.addOrder(item);
		order.addOrder(item1);
		double dis = discount.discount(order);
		assertEquals(60, dis);
	}

	@Test
	public void testFlat20_nonull () {
		assertThrows(IllegalArgumentException.class,() -> { discount.discount(null);});
		
		
	}
}
