package com.cos.comorizestart.web.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.comorizestart.config.auth.PrincipalDetails;
import com.cos.comorizestart.domain.image.Image;
import com.cos.comorizestart.service.ImageService;
import com.cos.comorizestart.web.dto.CMRespDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

	private final ImageService imageService;
	
	@GetMapping("/api/image")
	public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails){
		List<Image> images = imageService.이미지스토리(principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDTO<>(1, "성공", images), HttpStatus.OK);
	}
}
