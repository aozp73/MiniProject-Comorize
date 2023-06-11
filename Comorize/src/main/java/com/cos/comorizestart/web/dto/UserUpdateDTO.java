package com.cos.comorizestart.web.dto;

import javax.validation.constraints.NotBlank;

import com.cos.comorizestart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDTO {
	@NotBlank
	private String name;       // 필수값
	@NotBlank
	private String password; // 필수값
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	// 이 후 코드 수정 필요한 방식
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
