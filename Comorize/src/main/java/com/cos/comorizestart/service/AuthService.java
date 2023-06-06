package com.cos.comorizestart.service;

import org.springframework.stereotype.Service;

import com.cos.comorizestart.domain.user.User;
import com.cos.comorizestart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;
	
	public User 회원가입(User user) {
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
