package com.ems.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.ems.entity.User;
import com.ems.ems.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtService jwtService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

	public User registerUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	public String verify(User user) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
		
		if(authentication.isAuthenticated()) {return jwtService.generateJwtToken(user.getUserName());}
		
		return "failed";
	}
	
	
	

}
