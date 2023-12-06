package com.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstants;
import com.blog.entities.Post;
import com.blog.payloads.APIResponse;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.repositories.PostRepo;
import com.blog.service.FileService;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
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
	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue =AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
			{
		PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	//get post by id
	@GetMapping("/post/{postid}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable int postid){
		PostDTO postById = postService.getPostById(postid);
		return new ResponseEntity<PostDTO>(postById,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/post/{postId}")
	public APIResponse deletePost(@PathVariable int postId) {
		this.postService.deletePost(postId);
		
		return new APIResponse("Post Delete Successfully", true);
		
	}
	//update
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable int postId) {
		PostDTO updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
		
		
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> postByTitle(@PathVariable String keywords){
		List<PostDTO> result = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);
		
	}
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable int postId) throws IOException{
		
		PostDTO postDTO = this.postService.getPostById(postId);
		
		String filename = this.fileService.uploadImage(path, image);
		
		
		postDTO.setImageName(filename);
		PostDTO updatePost = this.postService.updatePost(postDTO, postId);
		
		return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
		
	}
	
}
