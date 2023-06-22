package com.cos.comorizestart.service;

import java.util.function.Supplier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.comorizestart.domain.subscribe.SubscribeRepository;
import com.cos.comorizestart.domain.user.User;
import com.cos.comorizestart.domain.user.UserRepository;
import com.cos.comorizestart.handler.ex.CustomException;
import com.cos.comorizestart.handler.ex.CustomValidationApiException;
import com.cos.comorizestart.web.dto.user.UserProfileDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserProfileDTO 회원프로필(int pageUserId, int principalId) {
		UserProfileDTO dto = new UserProfileDTO();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId == principalId);
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		
		dto.setSubscribeState(subscribeState == 1);
		dto.setSubscribeCount(subscribeCount);
		
		userEntity.getImages().forEach((image)->{
			image.setLikeCount(image.getLikes().size());
		});
		
		return dto;
	}
	
	@Transactional
	public User 회원수정(int id, User user) {
		// 1. 영속화
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
				return new CustomValidationApiException("찾을 수 없는 id입니다.");
		});
		
		// 2. 영속화된 오브젝트 수정 (더티 체킹 진행)
		userEntity.setName(user.getName());
		String rawPassword = user.getPassword();
		String encPaswword = bCryptPasswordEncoder.encode(rawPassword);
		
		userEntity.setPassword(user.getPassword());
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	} // 트랜잭션 끝남 : Dirty Checking -> 업데이트 완료
}
