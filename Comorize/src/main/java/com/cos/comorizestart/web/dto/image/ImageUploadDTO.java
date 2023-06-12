package com.cos.comorizestart.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageUploadDTO {
	private MultipartFile file;
	private String caption;
}
