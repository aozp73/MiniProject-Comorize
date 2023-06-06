package com.cos.comorizestart.web.dto.auth;

import com.cos.comorizestart.domain.user.User;

import lombok.Data;

@Data
public class SignupReq {
	private String username;
	private String password;
	private String email;
	private String name;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
