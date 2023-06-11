package com.cos.comorizestart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.comorizestart.handler.ex.CustomValidationAPIException;
import com.cos.comorizestart.handler.ex.CustomValidationException;
import com.cos.comorizestart.util.Script;
import com.cos.comorizestart.web.dto.CMRespDTO;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	// 클라이언트에게 직접 응답 시 사용
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		return Script.back(e.getErrorMap().toString());
	}
	
	// Ajax 통신 또는 Android 통신 때 앞단 개발자에게 응답 시 사용
	@ExceptionHandler(CustomValidationAPIException.class)
	public ResponseEntity<?> validationAPIException(CustomValidationAPIException e) {
		
		return new ResponseEntity<>(new CMRespDTO<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
}

