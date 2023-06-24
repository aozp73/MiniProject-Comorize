package com.cos.comorizestart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.comorizestart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Column(length = 20, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	private String website;
	private String bio;
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	private String role;
	
	// 연관관계의 주인이 아니며, 테이블에 컬럼을 만들지 말라는 의미
	// (연관관계의 주인은 Image Entity의 user임)
	
	// User를 Select할 때 해당 User id로 등록된 image들을 전략에 맞게 가져 오라는 의미
	// LAZY로 설정하면 User를 select할 때 기본적으로는 images를 호출하지 않음
	// (getImages() 함수를 호출한 뒤, images들을 호출할 때 select문 발동)
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"user"})
	private List<Image> images;
	
	private LocalDateTime createDate;
	
	@PrePersist // DB Insert 직전 실행
	public void createdDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role + "createDate="
				+ createDate + "]";
	}
	
	
}
