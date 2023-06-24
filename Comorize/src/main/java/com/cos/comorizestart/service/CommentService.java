package com.cos.comorizestart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.comorizestart.domain.comment.Comment;
import com.cos.comorizestart.domain.comment.CommentRepository;
import com.cos.comorizestart.domain.image.Image;
import com.cos.comorizestart.domain.user.User;
import com.cos.comorizestart.domain.user.UserRepository;
import com.cos.comorizestart.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public Comment 댓글쓰기(String content, int imageId, int userId) {
		
		Image image = new Image();
		image.setId(imageId);
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
		});
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(image);
		comment.setUser(userEntity);
		
		return commentRepository.save(comment);
	}
	
	@Transactional
	public void 댓글삭제(int id) {
		try {
			commentRepository.deleteById(id);
		} catch (Exception e) {
			throw new CustomApiException(e.getMessage());
		}
	}
}
