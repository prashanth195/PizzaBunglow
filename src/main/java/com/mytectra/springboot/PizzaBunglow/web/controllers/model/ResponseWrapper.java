package com.mytectra.springboot.PizzaBunglow.web.controllers.model;

public class ResponseWrapper<T> {

	private T repsonse;
	
	public enum Status {SUCCESS , FAILURE , ERROR};

	private Status status;

	public T getRepsonse() {
		return repsonse;
	}

	public void setRepsonse(T repsonse) {
		this.repsonse = repsonse;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ResponseWrapper(T repsonse, Status status) {
		super();
		this.repsonse = repsonse;
		this.status = status;
	}
	
}
