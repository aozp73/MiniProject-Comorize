package com.cos.comorizestart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.comorizestart.domain.likes.Likes;
import com.cos.comorizestart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String catpion;          // 오늘 나 너무 피곤해 (사진 설명)
	private String postImageUrl; // 사진을 전송받아서 그 사진을 서버 내 특정 폴더에 저장 (DB에 해당 경로 저장)
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image")
	private List<Likes> likes;
	
	private LocalDateTime createDate;
	
	@Transient // DB에 컬럼이 만들어지지 않음
	private boolean likeState;
	
	@Transient
	private int likeCount;
	
	@PrePersist 
	public void createdDate() {
		this.createDate = LocalDateTime.now();
	}

//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", catpion=" + catpion + ", postImageUrl=" + postImageUrl 
//				+ ", createDate=" + createDate + "]";
//	}
	
}
