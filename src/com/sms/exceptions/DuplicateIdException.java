package com.sms.exceptions;

public class DuplicateIdException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DuplicateIdException(String str) {
		super(str);
	}

}
