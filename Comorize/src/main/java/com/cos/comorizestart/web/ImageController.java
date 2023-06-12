package com.cos.comorizestart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.comorizestart.config.auth.PrincipalDetails;
import com.cos.comorizestart.service.ImageService;
import com.cos.comorizestart.web.dto.image.ImageUploadDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;
	
	@GetMapping({"/","/image/story"})
	public String story() {
		return "image/story";
	}
	
	@GetMapping("/image/popular")
	public String popular() {
		return "image/popular";
	}
	
	@GetMapping("/image/upload")
	public String upload() {
		return "image/upload";
	}
	
	@PostMapping("/image")
	public String imageUpload(ImageUploadDTO imageUploadDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		 
		imageService.사진업로드(imageUploadDTO, principalDetails);
		return "redirect:/user/"+principalDetails.getUser().getId();
	}
}