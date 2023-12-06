package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.blog.entities.Post;
import com.blog.payloads.APIResponse;
import com.blog.payloads.CommentDTO;
import com.blog.service.CommentService;

import jakarta.ws.rs.DELETE;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment,@PathVariable Integer postId){
		CommentDTO createComment = this.commentService.createComment(comment, postId);
		return new ResponseEntity<CommentDTO>(createComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<APIResponse> createComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<APIResponse>(new APIResponse("comment Deleted Successfully", true),HttpStatus.OK);
	}
}
