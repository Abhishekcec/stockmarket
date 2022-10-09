package com.estockmarket.authentication.service;

import com.estockmarket.authentication.model.User;

public interface UserService {
	
	String registerUser(User user);

	String logoutUser(String token);

}
