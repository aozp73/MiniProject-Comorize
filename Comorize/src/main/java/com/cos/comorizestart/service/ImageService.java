package com.cos.comorizestart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.comorizestart.config.auth.PrincipalDetails;
import com.cos.comorizestart.domain.image.ImageRepository;
import com.cos.comorizestart.web.dto.image.ImageUploadDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	@Value("${file.path}")
	private String uploadFolder;
	
	private final ImageRepository imageRepository;
	
	public void 사진업로드(ImageUploadDTO imageUploadDTO, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + imageUploadDTO.getFile().getOriginalFilename();
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		// 통신, I/O -> 예외 발생할 수 있으므로 아래와 같이 예외처리
		try {
			Files.write(imageFilePath, imageUploadDTO.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
