package com.mytectra.springboot.PizzaBunglow.Store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;

public class AddOnsStoreServiceTest {

private AddOnsStoreService store;
	
	@BeforeEach
	public void init() {
	 store= new AddOnsStoreService();
	}
	
	@Test
	public void test_AddingAllTypesOfPizza() {
		
		AddOns addOns= null;
		AddOns addOns1= new AddOns();
		AddOns addOns2= new AddOns(1, "Chicken Burger", "Chicken Burger with Spicy", 350);
		AddOns addOns3= new AddOns(0, "Chicken Burger", "Chicken Burger with Spicy", 350);
		AddOns addOns4= new AddOns(1, null, "Chicken Burger with Spicy", 350);
		AddOns addOns5= new AddOns(1, "   ", "Chicken Burger with Spicy", 350);
		AddOns addOns6= new AddOns(1, "Chicken Burger", null, 350);
		AddOns addOns7= new AddOns(1, "Chicken Burger", "   ", 350);
		AddOns addOns8= new AddOns(1, "Chicken Burger", "Chicken Burger with Spicy", 0);
		
		
		
		store.addAddOns(addOns);
		store.addAddOns(addOns1);
		store.addAddOns(addOns2);
		store.addAddOns(addOns3);
		store.addAddOns(addOns4);
		store.addAddOns(addOns5);
		store.addAddOns(addOns6);
		store.addAddOns(addOns7);
		store.addAddOns(addOns8);
		
		assertEquals(1, store.getAllAddOns().size());
	}
	
	@Test
	public void test_getNullPizza() throws AddOnsNotFoundException {
		
		assertThrows(AddOnsNotFoundException.class, () -> store.getAddOnsByName(null));
	}
	
	@Test
	public void test_getEmptyPizzaName() throws AddOnsNotFoundException {
		
		assertThrows(AddOnsNotFoundException.class, () -> store.getAddOnsByName("   "));
	}

	
	@Test
	public void test_getAddOnsByName() throws AddOnsNotFoundException {
		
		
		AddOns addOns2= new AddOns(1, "Chicken Burger", "Chicken Burger with Spicy", 350);
		
		
		store.addAddOns(addOns2);
		
		
		assertEquals(addOns2, store.getAddOnsByName("Chicken Burger"));
	}

	@Test
	public void test_getAddOnsByAddOnsNameFromEmptyList() throws AddOnsNotFoundException {
		
		assertThrows(AddOnsNotFoundException.class, () -> store.getAddOnsByName("Chicken Burger"));
	}	

	
	
}
