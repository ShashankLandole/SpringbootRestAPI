package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.APIResponse;
import com.blog.payloads.UserDTO;
import com.blog.service.UserService;







@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userdto){
		UserDTO createUserDto =  service.createUser(userdto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUser(){
		return new ResponseEntity(service.getAllusers(),HttpStatus.OK);
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable int id){
		UserDTO user =  service.getUserById(id);
		return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
		//return ResponseEntity.ok(service.getUserById(id));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable int id){
		UserDTO updateUser = service.updateUser(userDTO, id);
		return new ResponseEntity<UserDTO>(updateUser,HttpStatus.OK);
	}
	


	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable int id){
		service.deleteUser(id);
		
		return new ResponseEntity<APIResponse>(new APIResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	
}