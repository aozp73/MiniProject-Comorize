package com.cos.comorizestart.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.comorizestart.domain.user.User;
import com.cos.comorizestart.handler.ex.CustomValidationException;
import com.cos.comorizestart.service.AuthService;
import com.cos.comorizestart.web.dto.auth.SignupReq;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private final AuthService authservice;
	
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupReq signupReq, BindingResult bindingResult) {
		
			User user = signupReq.toEntity();
			User userEntity = authservice.회원가입(user);
			
			return "auth/signin";			
	
	}
}

