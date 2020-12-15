package com.mytectra.springboot.PizzaBunglow.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import com.mytectra.springboot.PizzaBunglow.model.Pizza;

@Component
public class CustomValidator implements SmartValidator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Pizza.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Pizza pizza = (Pizza) target;
		if(pizza.getCost() > 500)
		{
			errors.rejectValue("cost", "pizza.cost.failed");
		}
		
	}

	@Override
	public void validate(Object target, Errors errors, Object... validationHints) {
		validate(target, errors);
	}



}
