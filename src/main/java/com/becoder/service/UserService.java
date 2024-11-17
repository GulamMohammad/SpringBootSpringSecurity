package com.becoder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.becoder.repository.UserRepository;
import com.becoder.model.Users;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager manager;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	
	public Users registerUser(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}


	public String varify(Users user) {

		Authentication authentication = 
				manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}
		else
			return "Failed";
	}
	

}
