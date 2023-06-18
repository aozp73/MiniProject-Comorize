package com.cos.comorizestart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
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
	private final EntityManager em;
	
	@Transactional(readOnly = true)
	public List<SubscribeDTO> 구독리스트(int principalId, int pageUserId){
		
		// Query 준비
		StringBuffer sb = new StringBuffer();
		sb.append("select u.id userId, u.username, u.profileImageUrl,  ");
		sb.append("if( (select true from subscribe where fromUserId = ? and toUserId = u.id), true, false) subscribeState, ");  // principalDetails.user.id
		sb.append("if(u.id = ?, true, false) equalState "); // principalDetails.user.id
		sb.append("from subscribe f inner join user u on u.id = f.toUserId ");
		sb.append("where f.fromUserId = ? "); // pageUserId
		
		// Query 완성
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		// Query 실행 (이 부분에서 qlrm 라이브러리 활용: DTO에 DB조회 결과 매핑하기 위해)
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDTO> subscribeDTOs = result.list(query, SubscribeDTO.class);
		
		return subscribeDTOs;
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
