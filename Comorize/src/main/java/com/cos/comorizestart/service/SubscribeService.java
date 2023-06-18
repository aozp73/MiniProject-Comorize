package com.cos.comorizestart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.comorizestart.domain.subscribe.SubscribeRepository;
import com.cos.comorizestart.handler.ex.CustomApiException;
import com.cos.comorizestart.web.dto.subscribe.SubscribeDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	
	@Transactional(readOnly = true)
	public List<SubscribeDTO> 구독리스트(int principalId, int pageUserId){
		
		return null;
	}
	
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
			
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}		
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mUnSubscribe(fromUserId, toUserId);			
		} catch (Exception e) {
			throw new CustomApiException("이미 구독 취소를 하였습니다.");
		}		
	}
}
