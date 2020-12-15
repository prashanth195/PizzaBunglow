package com.mytectra.springboot.PizzaBunglow.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mytectra.springboot.PizzaBunglow.Store.AddOnStore;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaStore;


//@Configuration
public class TestConfig2 {

	@Bean
	public PizzaStore pStore() {
		return Mockito.mock(PizzaStore.class);
	}
	
	@Bean
	public AddOnStore aStore() {
		return Mockito.mock(AddOnStore.class);
	}
}
