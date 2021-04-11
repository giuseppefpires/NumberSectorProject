package com.numbersector.exception;

public class NumberNullOrEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6989264031517512694L;
	
	public NumberNullOrEmptyException() {
		super("Number cannot be empty");
	}

}
