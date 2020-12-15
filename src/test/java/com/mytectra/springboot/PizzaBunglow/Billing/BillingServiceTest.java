package com.mytectra.springboot.PizzaBunglow.Billing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mytectra.springboot.PizzaBunglow.config.TestConfig;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.OrderItem;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes =  { TestConfig.class, BillingServiceTest.class})
@Configuration
@ComponentScan(basePackages = "com.mytectra.springboot.PizzaBunglow.Billing")
@TestPropertySource("classpath:application.properties")
public class BillingServiceTest {
	
	@Autowired
	private Billing billingService;
	
	@Autowired
	private Discount disc1;
	
	@Autowired
	private Discount disc2;

	
	@Test
	public void testBilling() {
		
		PizzaOrder order = new PizzaOrder();
		OrderItem item = new OrderItem(new Pizza(1, "x piza", "", 100), 2);
		OrderItem item1 = new OrderItem(new AddOns(1, "x fries", "", 100), 2);
		order.addOrder(item);
		order.addOrder(item1);
		
		PizzaOrder order2 = new PizzaOrder();
		order2.addOrder(item);
		order2.addOrder(item1);
		
		Mockito.when(disc1.discount(Mockito.any(PizzaOrder.class))).thenReturn(40.0);
		Mockito.when(disc2.discount(Mockito.any(PizzaOrder.class))).thenReturn(10.0);
		
		
		billingService.bill(order2);
		
		assertNotNull(order2.getPrice());
		assertEquals(400, order2.getPrice().getCostPrice() , "Inccorect CP");
		assertEquals(50, order2.getPrice().getDiscount(),"Inccorect Dis");
		assertEquals(63, order2.getPrice().getTax(),"Inccorect tax");
		assertEquals(413, order2.getPrice().getFinalPrice(),"Inccorect fP");
	
		Mockito.verify(disc1).discount(order2);	
	Mockito.verify(disc2,Mockito.times(1)).discount(order2);	

	
	}

}
