package com.cos.comorizestart.web.dto.user;

import com.cos.comorizestart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDTO {
	private boolean pageOwnerState;
	private int imageCount;
	private User user;
}
