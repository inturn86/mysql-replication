package com.inturn.mysqlreplication.domain.dto.request;

import com.inturn.mysqlreplication.domain.entity.User;
import lombok.Builder;

@Builder
public record CreateUserRequestDTO(
		Long userId,
		String password,
		String userName
) {

	public User createUser() {
		return User.builder()
				.userName(userName())
				.password(password())
				.build();
	}
}
