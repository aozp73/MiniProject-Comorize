package com.cos.comorizestart.handler.ex;

import java.util.Map;

public class CustomValidationAPIException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMap;
	
	public CustomValidationAPIException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
	
	public CustomValidationAPIException(String message) {
		super(message);
	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
}
