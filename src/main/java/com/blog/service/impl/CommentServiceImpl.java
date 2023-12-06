package com.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.CommentDTO;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment = this.mapper.map(commentDTO,Comment.class);
		
		comment.setPost(post);
		
		Comment saveComment = this.commentRepo.save(comment);
		
		return this.mapper.map(saveComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", commentId));
		
		this.commentRepo.delete(comment);
		
	}

}
