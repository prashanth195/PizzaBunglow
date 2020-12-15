package com.mytectra.springboot.PizzaBunglow.PizzaKitchen;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mytectra.springboot.PizzaBunglow.Baker.Baker;
import com.mytectra.springboot.PizzaBunglow.Baker.PizzaBakeException;
import com.mytectra.springboot.PizzaBunglow.Billing.Billing;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.config.TestConfig3;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.AddOnsRequest;
import com.mytectra.springboot.PizzaBunglow.model.OrderItem;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder.Status;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequests;
import com.mytectra.springboot.PizzaBunglow.model.Price;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest.Base;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest.Size;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {TestConfig3.class, PizzaKitchenServiceTest.class})
@Configuration
@ComponentScan(basePackages = "com.mytectra.springboot.PizzaBunglow.PizzaKitchen")
public class PizzaKitchenServiceTest {

	@Autowired
	private Billing biller;
	
	@Autowired
	private Baker baker;
	
	@Autowired
	private PizzaKitchenService kitchen;
	
	//@Test
	public void test_Kitchen() throws PizzaNotFoundException, AddOnsNotFoundException {
		
		Pizza pizza= new Pizza(1, "Chicken Pizza", "Chicken Pizza with Spicy", 350);
		AddOns addOns= new AddOns(1, "Chicken Burger", "Chicken Burger with Spicy", 350);
		
		PizzaRequest pizzaRequest = new PizzaRequest();
		pizzaRequest.setBase(Base.NORMAL);
		pizzaRequest.setSize(Size.MEADIUM);
		pizzaRequest.setCount(2);
		pizzaRequest.setPizzaName("Chicken Pizza");
		
		AddOnsRequest addRequest = new AddOnsRequest();
		addRequest.setCount(3);
		addRequest.setName("Chicken Burger");
		
		PizzaRequests requests= new PizzaRequests();
		List<PizzaRequest> prequests= new ArrayList<PizzaRequest>();
		
		prequests.add(pizzaRequest);
		requests.setPizzaRequests(prequests);
		
		List<AddOnsRequest> arequests= new ArrayList<AddOnsRequest>();
		arequests.add(addRequest);
		
		OrderItem item= new OrderItem(pizza, 2);
		OrderItem item1= new OrderItem(addOns, 2);
		
		List<OrderItem> items= new ArrayList<OrderItem>();
		items.add(item);
		items.add(item1);
		
		PizzaOrder order = new PizzaOrder();
		order.setMessage("Thank You");
		order.setOrderId(1);
		order.setStatus(Status.PROCESSING);
		order.setOrderItem(items);
		
		try {
			Mockito.when(baker.bake(requests, arequests)).thenReturn(order);
		} catch (PizzaBakeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Price price= new Price();
		price.setCostPrice(1200);
		price.setDiscount(240);
		price.setTax(172.8);
		price.setFinalPrice(1132.8);
		
		order.setPrice(price);
		
		Mockito.doNothing().when(biller).bill(order);
		
		order=kitchen.AddOrder(requests, arequests, "8970932314", new Date());
		
		assertEquals(1200, order.getPrice().getCostPrice());
	}
	
	@Test
	public void test_Kitchen_throwException() throws PizzaNotFoundException, AddOnsNotFoundException {
		
		Pizza pizza= new Pizza(1, "Chicken Pizza", "Chicken Pizza with Spicy", 350);
		AddOns addOns= new AddOns(1, "Chicken Burger", "Chicken Burger with Spicy", 350);
		
		PizzaRequest pizzaRequest = new PizzaRequest();
		pizzaRequest.setBase(Base.NORMAL);
		pizzaRequest.setSize(Size.MEADIUM);
		pizzaRequest.setCount(2);
		pizzaRequest.setPizzaName("Chicken Pizza");
		
		AddOnsRequest addRequest = new AddOnsRequest();
		addRequest.setCount(3);
		addRequest.setName("Chicken Burger");
		
		PizzaRequests requests= new PizzaRequests();
		List<PizzaRequest> prequests= new ArrayList<PizzaRequest>();
		
		prequests.add(pizzaRequest);
		requests.setPizzaRequests(prequests);
		
		List<AddOnsRequest> arequests= new ArrayList<AddOnsRequest>();
		arequests.add(addRequest);
		
		OrderItem item= new OrderItem(pizza, 2);
		OrderItem item1= new OrderItem(addOns, 2);
		
		List<OrderItem> items= new ArrayList<OrderItem>();
		items.add(item);
		items.add(item1);
		
		PizzaOrder order = new PizzaOrder();
		order.setMessage("Thank You");
		order.setOrderId(1);
		order.setStatus(Status.PROCESSING);
		order.setOrderItem(items);
		
		try {
			Mockito.when(baker.bake(requests, arequests)).thenThrow(PizzaBakeException.class);
		} catch (PizzaBakeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Price price= new Price();
		price.setCostPrice(1200);
		price.setDiscount(240);
		price.setTax(172.8);
		price.setFinalPrice(1132.8);
		
		order.setPrice(price);
		
		Mockito.doNothing().when(biller).bill(order);*/
		
		order=kitchen.AddOrder(requests, arequests,"8970932314", new Date());
		
		assertEquals(null, order);
	}
	
}
