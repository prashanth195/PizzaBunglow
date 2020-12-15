package com.mytectra.springboot.PizzaBunglow.web.controllers.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mytectra.springboot.PizzaBunglow.web.controllers.model.Error;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.ResponseWrapper;
import com.mytectra.springboot.PizzaBunglow.web.controllers.model.ResponseWrapper.Status;

@RestControllerAdvice
public class ControllerAdvise {	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseWrapper<?>  handleBindingEx(MethodArgumentNotValidException obj) {
		BindingResult results = obj.getBindingResult();
		List<FieldError> errors = results.getFieldErrors();
		List<Error> errs = new ArrayList<Error>();
		for(FieldError err : errors) {
			String errMsg = String.format("%s Field is failed due to %s , value given is %s", err.getField() , err.getDefaultMessage() , err.getRejectedValue());
			Error errS = new Error(err.getField(),errMsg);
			errs.add(errS);
		}
		
		return  new ResponseWrapper<List<Error> >(errs, Status.FAILURE);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseWrapper<?>  handleBindingEx(Exception obj) {
		obj.printStackTrace();
		String errMsg= String.format(" %s Field is failed due to %s , value given is NULL", obj.getCause() , obj.getMessage() );
		Error err=new Error(obj.getLocalizedMessage(), errMsg);		
	
	return new ResponseWrapper<Error>(err, Status.FAILURE);
	}
	

}
