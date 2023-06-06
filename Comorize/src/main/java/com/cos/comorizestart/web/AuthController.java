package com.cos.comorizestart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.comorizestart.domain.user.User;
import com.cos.comorizestart.service.AuthService;
import com.cos.comorizestart.web.dto.auth.SignupReq;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
	public String signup(SignupReq signupReq) {
		log.info(signupReq.toString());
		
		User user = signupReq.toEntity();
		log.info(user.toString());
		
		User userEntity = authservice.회원가입(user);
		System.out.println(userEntity);
		return "auth/signin";
	}
}

