package com.blog.payloads;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;




public class UserDTO {
	


	private int id;


	private String name;
	

	private String email;
	
	
	private String password;
	
	
	private String about;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDTO() {
		
	}

	public UserDTO(String name, String email, String password, String about) {
	
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	
	
}
