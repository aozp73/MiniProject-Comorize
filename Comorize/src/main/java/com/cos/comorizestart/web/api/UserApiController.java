 package com.cos.comorizestart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.comorizestart.config.auth.PrincipalDetails;
import com.cos.comorizestart.domain.user.User;
import com.cos.comorizestart.handler.ex.CustomValidationAPIException;
import com.cos.comorizestart.service.UserService;
import com.cos.comorizestart.web.dto.CMRespDTO;
import com.cos.comorizestart.web.dto.UserUpdateDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	private final UserService userService;
	
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
	
			throw new CustomValidationAPIException("유효성검사 실패함", errorMap);
		} else {

			User userEntity = userService.회원수정(id, userUpdateDTO.toEntity());
			principalDetails.setUser(userEntity); // 세션 정보 변경
			
			return new CMRespDTO<>(1, "회원수정완료", userEntity);
		}	
	}
}
