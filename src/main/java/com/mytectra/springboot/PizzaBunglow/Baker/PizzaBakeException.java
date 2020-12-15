package com.mytectra.springboot.PizzaBunglow.Baker;

public class PizzaBakeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PizzaBakeException() {
	}

	public PizzaBakeException(String arg0) {
		super(arg0);
	}

	public PizzaBakeException(Throwable cause) {
		super(cause);
	}

	public PizzaBakeException(String message, Throwable cause) {
		super(message, cause);
	}

	public PizzaBakeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
