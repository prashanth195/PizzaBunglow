package com.mytectra.springboot.PizzaBunglow.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mytectra.springboot.PizzaBunglow.Store.AddOnStore;
import com.mytectra.springboot.PizzaBunglow.Store.AddOnsNotFoundException;

public class AddonsValidator implements ConstraintValidator<ValidAddons, String>{

	@Autowired
	private AddOnStore addOnStore;
	
	public AddonsValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		try {
			return addOnStore.getAddOnsByName(value)!=null;
		} catch (AddOnsNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
	}

}
