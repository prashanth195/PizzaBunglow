package com.mytectra.springboot.PizzaBunglow.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.Store.PizzaNotFoundException;
import com.mytectra.springboot.PizzaBunglow.Store.PizzaStore;

@Component
public class PizzaNameValidator implements ConstraintValidator<ValidPizza, String> {

	@Autowired
	private PizzaStore pizzaStore;
	
	public PizzaNameValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
			try {
				return pizzaStore.getPizzaByName(value) != null;
			} catch (PizzaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
		return false;
	}

}
