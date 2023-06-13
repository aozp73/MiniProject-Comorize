package com.cos.comorizestart.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.cos.comorizestart.domain.image.Image;
import com.cos.comorizestart.domain.user.User;

import lombok.Data;

@Data
public class ImageUploadDTO {
	private MultipartFile file;
	private String caption;
	
	public Image toEntity(String postImageUrl, User user) {
		return Image.builder()
				.catpion(caption)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	}
}
