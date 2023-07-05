package com.blog.service;

import java.util.List;

import com.blog.payloads.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user,Integer userId);
	UserDTO getUserById(Integer id);
	
	List<UserDTO> getAllusers();
	
	void deleteUser(Integer id);
}
