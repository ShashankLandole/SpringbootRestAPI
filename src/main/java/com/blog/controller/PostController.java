package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Post;
import com.blog.payloads.PostDTO;
import com.blog.repositories.PostRepo;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDTO> createPost(
			@RequestBody PostDTO postDTO,
			@PathVariable int userId,
			@PathVariable int categoryId){
			
		PostDTO post = this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(post,HttpStatus.CREATED);
		
	}
	
	//get by user
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable int userid){
		
		List<PostDTO> postByUser = postService.getPostByUser(userid);
		
		return new ResponseEntity<List<PostDTO>>(postByUser,HttpStatus.OK);
		
	}
	
	
	//get by category
	
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable int categoryid){
		
		List<PostDTO> postByCategory = postService.getPostByCategory(categoryid);
		
		return new ResponseEntity<List<PostDTO>>(postByCategory,HttpStatus.OK);
		
	}
}
