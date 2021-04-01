package com.numbersector.exception;

public class SectorRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4838704634222392238L;

	public SectorRequestException(String message) {
		super(message);
	}

	public SectorRequestException(String message, Exception e) {
		super(message);
	}
}
