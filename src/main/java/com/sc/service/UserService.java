package com.sc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sc.entity.UserInfo;
import com.sc.repository.UserInfoRepository;

@Service
public class UserService {

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<UserInfo> getAllUsers() {
		return repository.findAll();
	}

	public UserInfo getUser(int id) {
		Optional<UserInfo> userInfo =  repository.findById(id);
		
		if (userInfo.isPresent()) {
			return userInfo.get();
		}
		
		throw new RuntimeException("User details not found for id " + id);
	}

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "user added to successfully ";
	}
}
