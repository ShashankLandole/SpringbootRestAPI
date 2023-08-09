package com.blog.service;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDTO;

public interface PostService {
	
	PostDTO createPost(PostDTO postDto,int userId,int categoryId);
	
	Post updatePost(PostDTO postDto,int id);
	
	void deletePost(int id);
	
	List<Post> getAllPost();
	
	Post getPostById(int id);
	
	//get all post by category
	List<PostDTO> getPostByCategory(int categoryId);
	
	//get all post by user
	List<PostDTO> getPostByUser(int userId);
	
	//search any post
	List<Post> searchPost(String keyword);

}
