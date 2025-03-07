package com.ems.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.ems.entity.User;
import com.ems.ems.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return  ResponseEntity.ok(userService.registerUser(user));
		
	}
	
	@PostMapping("/loginuser")
	public String login(@RequestBody User user) {
		return  userService.verify(user);
		
	}

}
