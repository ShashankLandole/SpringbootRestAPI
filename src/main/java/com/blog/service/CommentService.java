package com.blog.service;

import com.blog.payloads.CommentDTO;

public interface CommentService {
	CommentDTO createComment(CommentDTO commentDTO,Integer postId);
	void deleteComment(Integer commentId);
}
