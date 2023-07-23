package com.blog.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.UserDTO;
import com.blog.repositories.UserRepository;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = this.dtoToUser(userDto);
		
		User savedUser = userRepository.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		User user =userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepository.save(user);
		UserDTO userdto1 = this.userToDto(updatedUser);
		
		return userdto1;
	}

	@Override
	public UserDTO getUserById(Integer id) {
		User user =userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllusers() {
		List<User> users = this.userRepository.findAll();
		
		//convert all users to dto
		List<UserDTO> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		
		return userDto;
	}

	@Override
	public void deleteUser(Integer id) {
		User user =userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		userRepository.deleteById(id);
	}
	
	private User dtoToUser(UserDTO userDto) {
		

		
		User user = this.modelMapper.map(userDto, User.class);
		
		
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
private UserDTO userToDto(User user) {
		
		UserDTO userDto = this.modelMapper.map(user, UserDTO.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
	

}
