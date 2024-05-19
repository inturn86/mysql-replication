package com.inturn.mysqlreplication.domain.controller;

import com.inturn.mysqlreplication.domain.dto.request.CreateUserRequestDTO;
import com.inturn.mysqlreplication.domain.entity.User;
import com.inturn.mysqlreplication.domain.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public void saveUser(@RequestBody CreateUserRequestDTO req) {
		userService.save(req);
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.getUser(userId);
	}


}
