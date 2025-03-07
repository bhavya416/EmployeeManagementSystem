package com.ems.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.ems.entity.PrincipalUser;
import com.ems.ems.entity.User;
import com.ems.ems.repo.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepo.findByUserName(username);
	
		
		if(user==null) {
			throw new UsernameNotFoundException("User not Found" +username);
		}
		
		return new PrincipalUser(user);
		
	}

}
