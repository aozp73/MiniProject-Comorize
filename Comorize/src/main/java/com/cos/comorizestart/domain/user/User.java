package com.cos.comorizestart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // DB에 테이블 생성
public class User {
	@Id
	// 번호 증가 전략이 DB를 따라감 (MySQL : AutoIncrement, Oracle : Sequence 등)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;
	
	private String name;
	private String website;
	private String bio;
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	private String role;
	
	private LocalDateTime createDate;
	
	@PrePersist // DB Insert 직전 실행
	public void createdDate() {
		this.createDate = LocalDateTime.now();
	}
}
