package com.mytectra.springboot.PizzaBunglow.Billing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.OrderItem;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;

public class BuyOneGetOneDiscountTest {

	private static BuyOneGetOneDiscount discount;
	
	private PizzaOrder order;
	
	@BeforeAll
	public static void init() {
		discount= new BuyOneGetOneDiscount();
	}
	
	@BeforeEach
	public void init2() {
		order= new PizzaOrder();
	}
	
		@Test
		public void test1() {
			
			OrderItem item= new OrderItem(new Pizza(1, "x_pizza","",100), 2);
			OrderItem item1=new OrderItem(new AddOns(1, "x_coke","",100), 2);
			
			order.addOrder(item1);
			order.addOrder(item);
			
			double dis= discount.discount(order);
			
			assertEquals(0, dis);
			
		}
		
		@Test
		public void test2_paneer_fries() {
			
			OrderItem item= new OrderItem(new Pizza(1, "Panner Pizza","",100), 2);
			OrderItem item1=new OrderItem(new AddOns(1, "Fries","",100), 2);
			
			order.addOrder(item1);
			order.addOrder(item);
			
			double dis= discount.discount(order);
			
			assertEquals(0, dis);
			
		}
}
