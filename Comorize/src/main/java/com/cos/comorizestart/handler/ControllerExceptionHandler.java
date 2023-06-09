package com.cos.comorizestart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.comorizestart.handler.ex.CustomValidationException;
import com.cos.comorizestart.util.Script;
import com.cos.comorizestart.web.dtoC.CMRespDTO;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		return Script.back(e.getErrorMap().toString());
	}
}

