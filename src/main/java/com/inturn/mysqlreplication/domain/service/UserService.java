package com.inturn.mysqlreplication.domain.service;

import com.inturn.mysqlreplication.domain.dto.request.CreateUserRequestDTO;
import com.inturn.mysqlreplication.domain.entity.User;
import com.inturn.mysqlreplication.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public User save(CreateUserRequestDTO req) {
		return userRepository.save(req.createUser());
	}

	@Transactional(readOnly = true)
	public User getUser(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

}
