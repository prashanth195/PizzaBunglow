package com.mytectra.springboot.PizzaBunglow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.mytectra.springboot.PizzaBunglow.PizzaKitchen.PizzaKitchenService;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaStore;

@SpringBootApplication
@EntityScan(basePackages = { "com.mytectra.springboot.PizzaBunglow.model","com.mytectra.springboot.PizzaBunglow.Security.Entity"})
@EnableAspectJAutoProxy
public class PizzaBunglowApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PizzaBunglowApplication.class, args);
		PizzaKitchenService pizzaKit = ctx.getBean(PizzaKitchenService.class);
		
		 PizzaStore pizzaStore = ctx.getBean(PizzaStore.class);

		/*Pizza pizza= new Pizza();
		pizza.setId(1);
		pizza.setName("Panner Pizza");
		pizza.setCost(300);
		pizza.setDescription("topped with panner");
		
		Pizza pizza1= new Pizza();
		pizza1.setId(2);
		pizza1.setName("Chicken Pizza");
		pizza1.setCost(450);
		pizza1.setDescription("topped with chicken");
		
		pizzaStore.addPizza(pizza);
		pizzaStore.addPizza(pizza1);
		
		AddOns addOns1= new AddOns();
		addOns1.setId(1);
		addOns1.setName("Coke");
		addOns1.setDescription("Cool drinks");
		addOns1.setCost(30);
		
		AddOns addOns2= new AddOns();
		addOns2.setId(2);
		addOns2.setName("Fries");
		addOns2.setDescription("French Fries");
		addOns2.setCost(50);
		
		AddOns addOns3= new AddOns();
		addOns3.setId(3);
		addOns3.setName("Chicken Burger");
		addOns3.setDescription("Burger with Chicken");
		addOns3.setCost(60);
		
		AddOnStore addOnstore= ctx.getBean(AddOnStore.class);
		addOnstore.addAddOns(addOns1);
		addOnstore.addAddOns(addOns2);
		addOnstore.addAddOns(addOns3);*/
		
	/*	PizzaRequest pizzaRequest = new PizzaRequest();
		pizzaRequest.setBase(Base.THIN);
		pizzaRequest.setPizzaName("Panner Pizza");
		pizzaRequest.setSize(Size.SMALL);
		pizzaRequest.setCount(2);
		
		PizzaRequest pizzaRequest1 = new PizzaRequest();
		pizzaRequest1.setBase(Base.THIN);
		pizzaRequest1.setPizzaName("Chicken Pizza");
		pizzaRequest1.setSize(Size.MEADIUM);
		pizzaRequest1.setCount(2);
		
		List<PizzaRequest> requestList= new ArrayList<PizzaRequest>();
		requestList.add(pizzaRequest);
		requestList.add(pizzaRequest1);
		
		PizzaRequests requests= new PizzaRequests();
		requests.setPizzaRequests(requestList);
		
		AddOns addOns1= new AddOns();
		addOns1.setName("Coke");
		addOns1.setDescription("Cool drinks");
		addOns1.setCost(30);
		
		AddOns addOns2= new AddOns();
		addOns2.setName("Fries");
		addOns2.setDescription("French Fries");
		addOns2.setCost(50);
		
		AddOns addOns3= new AddOns();
		addOns3.setName("Chicken Burger");
		addOns3.setDescription("Burger with Chicken");
		addOns3.setCost(60);
		
		AddOnStore addOnstore= ctx.getBean(AddOnStore.class);
		addOnstore.addAddOns(addOns1);
		addOnstore.addAddOns(addOns2);
		addOnstore.addAddOns(addOns3);
		
		AddOnsRequest addOnsRequest= new AddOnsRequest();
		addOnsRequest.setName("Coke");
		addOnsRequest.setCount(3);
		
		
		
		AddOnsRequest addOnsRequest1= new AddOnsRequest();
		addOnsRequest1.setName("Fries");
		addOnsRequest1.setCount(2);
		
		
		
		AddOnsRequest addOnsRequest2= new AddOnsRequest();
		addOnsRequest2.setName("Chicken Burger");
		addOnsRequest2.setCount(3);
		
		
		List<AddOnsRequest> AddOnsList= new ArrayList<AddOnsRequest>();
		AddOnsList.add(addOnsRequest);
		AddOnsList.add(addOnsRequest1);
		AddOnsList.add(addOnsRequest2);
		
		PizzaOrder recipt;
		try {
			recipt = pizzaKit.Order(requests, AddOnsList);
			
			System.out.println("OrderId is "+recipt.getOrderId());
			System.out.println("Order Contents is");
			for(OrderItem OT: recipt.getOrderItem()) {
				if(OT.getPizza()!=null) {
				System.out.println(OT.getPizza().getName()+"-"+OT.getCount());
				}
				
				if(OT.getAddOns()!=null) {
					System.out.println(OT.getAddOns().getName()+"-"+OT.getCount());
				}
			}
			System.out.println("Order Status is "+recipt.getStatus());
			System.out.println("Order Cost Price  "+recipt.getPrice().getCostPrice());
			System.out.println("Order Discount  "+recipt.getPrice().getDiscount());
			System.out.println("Order Tax in Rs  "+recipt.getPrice().getTax());
			System.out.println("Order Final Cost "+recipt.getPrice().getFinalPrice());
			System.out.println(recipt.getMessage());*/
			
		/*} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
	
	}

}

