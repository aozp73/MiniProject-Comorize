package com.cos.comorizestart.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException{

    // 객체를 JVM이 구분할 때 사용 (현재 중요 x)
	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMap;
	
	public CustomValidationException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}
	
	public Map<String, String> getErrorMap(){
		return errorMap;
	}
}
