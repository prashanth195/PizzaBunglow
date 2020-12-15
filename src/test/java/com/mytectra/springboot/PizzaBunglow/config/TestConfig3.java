package com.mytectra.springboot.PizzaBunglow.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mytectra.springboot.PizzaBunglow.Baker.Baker;
import com.mytectra.springboot.PizzaBunglow.Billing.Billing;
import com.mytectra.springboot.PizzaBunglow.Billing.Discount;

@Configuration
public class TestConfig3 {

	@Bean
	public Billing biller() {
		return Mockito.mock(Billing.class);
	}
	
	@Bean
	public Baker baker() {
		return Mockito.mock(Baker.class);
	}
}
