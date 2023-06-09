package com.cos.comorizestart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.comorizestart.handler.ex.CustomValidationException;
import com.cos.comorizestart.web.dtoC.CMRespDTO;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public CMRespDTO<?> validationException(CustomValidationException e) {
		return new CMRespDTO<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
	}
}

