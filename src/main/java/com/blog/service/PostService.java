package com.blog.service;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;

public interface PostService {
	
	PostDTO createPost(PostDTO postDto,int userId,int categoryId);
	
	PostDTO updatePost(PostDTO postDto,int id);
	
	void deletePost(int id);
	
	PostResponse getAllPost(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	PostDTO getPostById(int id);
	
	//get all post by category
	List<PostDTO> getPostByCategory(int categoryId);
	
	//get all post by user
	List<PostDTO> getPostByUser(int userId);
	
	//search any post
	List<PostDTO> searchPost(String keyword);

}
