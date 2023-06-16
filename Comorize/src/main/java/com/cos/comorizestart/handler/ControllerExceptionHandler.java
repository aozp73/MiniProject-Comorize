package com.cos.comorizestart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.comorizestart.handler.ex.CustomApiException;
import com.cos.comorizestart.handler.ex.CustomException;
import com.cos.comorizestart.handler.ex.CustomValidationApiException;
import com.cos.comorizestart.handler.ex.CustomValidationException;
import com.cos.comorizestart.util.Script;
import com.cos.comorizestart.web.dto.CMRespDTO;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e) {
		
		return new ResponseEntity<>(new CMRespDTO<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
	
	// 클라이언트에게 직접 응답 시 사용
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		if (e.getErrorMap() == null) {
			
			return Script.back(e.getMessage());
		}else {
			
			return Script.back(e.getErrorMap().toString());
		}
	}
	
	// 클라이언트에게 직접 응답 시 사용
	@ExceptionHandler(CustomException.class)
	public String Exception(CustomException e) {
			return Script.back(e.getMessage());

	}
	
	// Ajax 통신 또는 Anzdroid 통신 때 앞단 개발자에게 응답 시 사용
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		
		return new ResponseEntity<>(new CMRespDTO<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	

}

