package com.cos.comorizestart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.comorizestart.config.auth.PrincipalDetails;
import com.cos.comorizestart.domain.comment.Comment;
import com.cos.comorizestart.handler.ex.CustomValidationApiException;
import com.cos.comorizestart.service.CommentService;
import com.cos.comorizestart.web.dto.CMRespDTO;
import com.cos.comorizestart.web.dto.comment.CommentDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

	private final CommentService commentService;
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(@Valid @RequestBody CommentDTO commentDTO, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		
		Comment comment = commentService.댓글쓰기(commentDTO.getContent(), commentDTO.getImageId(), 
				principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDTO<>(1, "댓글쓰기성공", comment), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		commentService.댓글삭제(id);
		return new ResponseEntity<>(new CMRespDTO<>(1, "댓글삭제성공", null), HttpStatus.OK);
	}
}
