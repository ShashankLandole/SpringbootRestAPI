package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Category;
import com.blog.entities.Comment;
import com.blog.entities.User;

import jakarta.persistence.ManyToOne;

public class PostDTO {
	
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;

	private CategoryDTO category;
	
	
	private UserDTO user;

	private Set<CommentDTO> comments = new HashSet<>();
	
	
	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public Date getAddedDate() {
		return addedDate;
	}


	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}


	public CategoryDTO getCategory() {
		return category;
	}


	public void setCategory(CategoryDTO category) {
		this.category = category;
	}


	public UserDTO getUser() {
		return user;
	}


	public void setUser(UserDTO user) {
		this.user = user;
	}


	public Set<CommentDTO> getComments() {
		return comments;
	}


	public void setComments(Set<CommentDTO> comments) {
		this.comments = comments;
	}


	public PostDTO(String title, String content, String imageName, Date addedDate, CategoryDTO category, UserDTO user) {
		super();
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
	}


	public PostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
