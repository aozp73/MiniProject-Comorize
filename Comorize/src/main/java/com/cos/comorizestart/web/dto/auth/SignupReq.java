package com.cos.comorizestart.web.dto.auth;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import com.cos.comorizestart.domain.user.User;

import lombok.Data;

@Data
public class SignupReq {
	@Max(20)
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
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
