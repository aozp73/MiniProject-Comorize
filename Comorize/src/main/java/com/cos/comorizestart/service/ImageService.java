package com.cos.comorizestart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.comorizestart.config.auth.PrincipalDetails;
import com.cos.comorizestart.domain.image.Image;
import com.cos.comorizestart.domain.image.ImageRepository;
import com.cos.comorizestart.domain.user.UserRepository;
import com.cos.comorizestart.web.dto.image.ImageUploadDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	@Value("${file.path}")
	private String uploadFolder;
	
	private final ImageRepository imageRepository;
	
	@Transactional(readOnly = true) // readOnly : 읽기 전용, 변경감지&더티체키&flush x
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStory(principalId, pageable);
		return images;
	}
	
	
	@Transactional
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
		
		// image 테이블에 저장
		Image image = imageUploadDTO.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
//		System.out.println(imageEntity.getUser());
//		System.out.println(imageEntity.getUser().getName());
	}
}
