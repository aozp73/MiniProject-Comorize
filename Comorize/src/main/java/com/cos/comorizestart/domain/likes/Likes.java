package com.cos.comorizestart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.comorizestart.domain.image.Image;
import com.cos.comorizestart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="likes_uk",
						columnNames = {"imageId", "userId"}
				)
		}
)
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "ImageId")
	@ManyToOne
	private Image image; // 하나의 좋아요는 하나의 이미지, 하나의 이미지는 여러개의 좋아요
	
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user; // 한명은 여러개의 좋아요, 하나의 좋아뇨는 한명만
	
	private LocalDateTime createDate;
	
	@PrePersist 
	public void createdDate() {
		this.createDate = LocalDateTime.now();
	}
}
