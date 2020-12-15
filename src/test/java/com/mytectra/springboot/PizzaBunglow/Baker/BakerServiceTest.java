package com.mytectra.springboot.PizzaBunglow.Baker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
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

import com.mytectra.springboot.PizzaBunglow.Store.AddOnStore;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaStore;
import com.mytectra.springboot.PizzaBunglow.config.TestConfig2;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.AddOnsRequest;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;
import com.mytectra.springboot.PizzaBunglow.model.PizzaOrder;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest.Base;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequest.Size;
import com.mytectra.springboot.PizzaBunglow.model.PizzaRequests;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes= {TestConfig2.class, BakerServiceTest.class})
@Configuration
@ComponentScan(basePackages = "com.mytectra.springboot.PizzaBunglow.Baker")
public class BakerServiceTest {
	
		
	@Autowired
	private PizzaStore pStore;
	
	@Autowired
	private AddOnStore aStore;
	
	@Autowired
	private BakerService baker;
	
	@AfterEach
	public void resetMocks() {
		Mockito.reset(pStore,aStore);
	}
	
	@Test
	public void test_baker() throws PizzaBakeException, PizzaNotFoundException, AddOnsNotFoundException {
		
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
		
		Mockito.when(pStore.getPizzaByName(pizzaRequest.getPizzaName())).thenReturn(pizza);
		Mockito.when(aStore.getAddOnsByName(addRequest.getName())).thenReturn(addOns);
		
		PizzaRequests requests= new PizzaRequests();
		List<PizzaRequest> prequests= new ArrayList<PizzaRequest>();
		
		prequests.add(pizzaRequest);
		requests.setPizzaRequests(prequests);
		
		List<AddOnsRequest> arequests= new ArrayList<AddOnsRequest>();
		arequests.add(addRequest);
		
		PizzaOrder order= new PizzaOrder();
		 order=	baker.bake(requests, arequests);
		
		
		
		assertEquals(2, order.getOrderItem().size());
		assertEquals("Thank You!", order.getMessage());
		
	}
	
	@Test
	public void test_baker_NullPizza() throws AddOnsNotFoundException {
		
		
		AddOns addOns= new AddOns(1, "Chicken Burger", "Chicken Burger with Spicy", 350);
		
		PizzaRequest pizzaRequest = new PizzaRequest();
		pizzaRequest.setBase(Base.NORMAL);
		pizzaRequest.setSize(Size.MEADIUM);
		pizzaRequest.setCount(2);
		pizzaRequest.setPizzaName("Chicken Pizza");
		
		AddOnsRequest addRequest = new AddOnsRequest();
		addRequest.setCount(3);
		addRequest.setName("Chicken Burger");
		
		
		Mockito.when(aStore.getAddOnsByName(addRequest.getName())).thenReturn(addOns);
		
		PizzaRequests requests= new PizzaRequests();
		List<PizzaRequest> prequests= new ArrayList<PizzaRequest>();
		
		prequests.add(pizzaRequest);
		requests.setPizzaRequests(prequests);
		
		List<AddOnsRequest> arequests= new ArrayList<AddOnsRequest>();
		arequests.add(addRequest);
		
		assertThrows(PizzaBakeException.class, () -> baker.bake(requests, arequests));
		
	}
	
	@Test
	public void test_bakerNullAddOns() throws PizzaNotFoundException {
		
		Pizza pizza= new Pizza(1, "Chicken Pizza", "Chicken Pizza with Spicy", 350);
		
		PizzaRequest pizzaRequest = new PizzaRequest();
		pizzaRequest.setBase(Base.NORMAL);
		pizzaRequest.setSize(Size.MEADIUM);
		pizzaRequest.setCount(2);
		pizzaRequest.setPizzaName("Chicken Pizza");
		
		AddOnsRequest addRequest = new AddOnsRequest();
		addRequest.setCount(3);
		addRequest.setName("Chicken Burger");
		
		Mockito.when(pStore.getPizzaByName(pizzaRequest.getPizzaName())).thenReturn(pizza);
			
		PizzaRequests requests= new PizzaRequests();
		List<PizzaRequest> prequests= new ArrayList<PizzaRequest>();
		
		prequests.add(pizzaRequest);
		requests.setPizzaRequests(prequests);
		
		List<AddOnsRequest> arequests= new ArrayList<AddOnsRequest>();
		arequests.add(addRequest);
		
		assertThrows(PizzaBakeException.class, () -> baker.bake(requests, arequests));
	}
	
	
}
