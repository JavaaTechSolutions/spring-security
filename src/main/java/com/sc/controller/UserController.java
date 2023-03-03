package com.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.entity.UserInfo;
import com.sc.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/isRunning")
	public String isRunning() {
		return "Service is running";
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/getAllUser")
	public List<UserInfo> getAllUser() {
		return userService.getAllUsers();
	}

	@PostMapping("/addUser")
	public String addUser(@RequestBody UserInfo userInfo) {
		return userService.addUser(userInfo);
	}
}
