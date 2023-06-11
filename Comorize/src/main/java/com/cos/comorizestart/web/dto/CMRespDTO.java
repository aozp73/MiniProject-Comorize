package com.cos.comorizestart.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDTO<T> {
	private int code; // 코드 응답 -> 1(성공), -1(실패)
	private String message;
	private T data;
}

