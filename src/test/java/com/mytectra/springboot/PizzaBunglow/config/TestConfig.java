package com.mytectra.springboot.PizzaBunglow.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mytectra.springboot.PizzaBunglow.Billing.Discount;

@Configuration
public class TestConfig {

	@Bean
	public Discount disc1() {
		return Mockito.mock(Discount.class);
	}
	
	@Bean
	public Discount disc2() {
		return Mockito.mock(Discount.class);
	}
}
