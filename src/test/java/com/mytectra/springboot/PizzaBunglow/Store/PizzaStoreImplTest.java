package com.mytectra.springboot.PizzaBunglow.Store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.BeanValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mytectra.springboot.PizzaBunglow.Baker.PizzaBakeException;
import com.mytectra.springboot.PizzaBunglow.PizzaKitchen.PizzaKitchenServiceTest;
import com.mytectra.springboot.PizzaBunglow.config.TestConfig3;
import com.mytectra.springboot.PizzaBunglow.model.AddOns;
import com.mytectra.springboot.PizzaBunglow.model.Pizza;

@ExtendWith(MockitoExtension.class)
public class PizzaStoreImplTest{
	
	@InjectMocks
	private PizzaStoreImpl store;
	
	@Spy
	private Validator valid =  Validation.buildDefaultValidatorFactory().getValidator();	
	
	@Test
	public void test_AddingAllTypesOfPizza() {
		
		Pizza pizza= null;
		Pizza pizza1= new Pizza();
		Pizza pizza2= new Pizza(1, "Chicken Pizza", "Chicken Pizza with Spicy", 350);
		Pizza pizza3= new Pizza(0, "Chicken Pizza", "Chicken Pizza with Spicy", 350);
		Pizza pizza4= new Pizza(1, null, "Chicken Pizza with Spicy", 350);
		Pizza pizza5= new Pizza(1, "   ", "Chicken Pizza with Spicy", 350);
		Pizza pizza6= new Pizza(1, "Chicken Pizza", null, 350);
		Pizza pizza7= new Pizza(1, "Chicken Pizza", "   ", 350);
		Pizza pizza8= new Pizza(1, "Chicken Pizza", "Chicken Pizza with Spicy", 0);
		
		
		
		//store.addPizza(pizza);
		store.addPizza(pizza1);
		store.addPizza(pizza3);
		store.addPizza(pizza2);
		store.addPizza(pizza4);
		store.addPizza(pizza5);
		store.addPizza(pizza6);
		store.addPizza(pizza7);
		store.addPizza(pizza8);
		
		assertEquals(2, store.getAllPizzas().size());
	}
	
	@Test
	public void test_getNullPizza() throws PizzaNotFoundException {
		
		assertThrows(PizzaNotFoundException.class, () -> store.getPizzaByName(null));
	}
	
	@Test
	public void test_getEmptyPizzaName() throws PizzaNotFoundException {
		
		assertThrows(PizzaNotFoundException.class, () -> store.getPizzaByName("   "));
	}

	
	@Test
	public void test_getPizzaByPizzaName() throws PizzaNotFoundException {
		
		
		Pizza pizza2= new Pizza(1, "Chicken Pizza", "Chicken Pizza with Spicy", 350);
		
		
		store.addPizza(pizza2);
		
		
		assertEquals(pizza2, store.getPizzaByName("Chicken Pizza"));
	}

	@Test
	public void test_getPizzaByPizzaNameFromEmptyList() throws PizzaNotFoundException {
		
		assertThrows(PizzaNotFoundException.class, () ->  store.getPizzaByName("Chicken Pizza"));
	}
	
	
}
