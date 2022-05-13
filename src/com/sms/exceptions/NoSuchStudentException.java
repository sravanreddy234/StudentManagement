package com.sms.exceptions;

public class NoSuchStudentException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NoSuchStudentException(String str) {
		super(str);
	}
}
