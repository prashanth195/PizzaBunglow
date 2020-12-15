package com.mytectra.springboot.PizzaBunglow.web.controllers.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName ="request" ,  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeBean {
private int counter;		

private String client;	
	
@PostConstruct	
  public void init() {
	  System.out.println("Created one");
  }
 
@PreDestroy
  public void des() {
	  System.out.println("Destroyed");
  }


public String getClient() {
	return client;
}

public void setClient(String client) {
	this.client = client;
}

public int getCount() {
	return counter;
}

public void count() {
	counter++;
}
}
