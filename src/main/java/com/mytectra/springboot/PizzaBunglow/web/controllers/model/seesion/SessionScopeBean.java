/*package com.mytectra.springboot.PizzaBunglow.web.controllers.model.seesion;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


*//**
 * No tot be used for ReST sevices;
 * @author Sajan
 *
 *//*
@Component
@Scope(scopeName ="session" ,  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopeBean {

private int counter;	
	
@PostConstruct	
  public void init() {
	  System.out.println("Session Created one");
  }
 
@PreDestroy
  public void des() {
	  System.out.println("Session Destroyed");
  }


public int getCount() {
	return counter;
}

public void count() {
	counter++;
}
}
*/