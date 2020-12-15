package com.mytectra.springboot.PizzaBunglow.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PizzaNameValidator.class)
public @interface ValidPizza {
	
	 String message() default "";

	 Class<?>[] groups() default {};
	 
	 
	 Class<? extends Payload>[] payload() default { };



}
