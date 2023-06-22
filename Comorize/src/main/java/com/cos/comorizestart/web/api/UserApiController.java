 package com.cos.comorizestart.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.comorizestart.config.auth.PrincipalDetails;
import com.cos.comorizestart.domain.user.User;
import com.cos.comorizestart.handler.ex.CustomValidationApiException;
import com.cos.comorizestart.service.SubscribeService;
import com.cos.comorizestart.service.UserService;
import com.cos.comorizestart.web.dto.CMRespDTO;
import com.cos.comorizestart.web.dto.subscribe.SubscribeDTO;
import com.cos.comorizestart.web.dto.user.UserUpdateDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	private final SubscribeService subscribeService;
	
	@PutMapping("/api/user/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile,
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		
		User userEntiUser = userService.회원프로필사진변경(principalId, profileImageFile);
		principalDetails.setUser(userEntiUser);
		return new ResponseEntity<>(new CMRespDTO<>(1, "프로필사진변경 성공", null), HttpStatus.OK);
		
	}
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		List<SubscribeDTO> subscribeDTO = subscribeService.구독리스트(principalDetails.getUser().getId(), pageUserId);
		
		return new ResponseEntity<>(new CMRespDTO<>(1, "구독자 정보 리스트 가져오기 성공", subscribeDTO), HttpStatus.OK);
	}
	
	@PutMapping("/api/user/{id}")
	public CMRespDTO<?> update(
			@PathVariable int id, 
			@Valid UserUpdateDTO userUpdateDTO, 
			BindingResult bindingResult, // @Valid 적혀있는 다음 위치에 적어야 함
			@AuthenticationPrincipal PrincipalDetails principalDetails) {

		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for (FieldError error: bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
	
			throw new CustomValidationApiException("유효성검사 실패함", errorMap);
		} else {

			User userEntity = userService.회원수정(id, userUpdateDTO.toEntity());
			principalDetails.setUser(userEntity); // 세션 정보 변경
			
			return new CMRespDTO<>(1, "회원수정완료", userEntity);
		}	
	}
}
