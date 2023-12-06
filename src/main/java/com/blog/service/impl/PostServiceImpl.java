package com.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.exception.SearchResultNotFound;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.payloads.UserDTO;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepository;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDTO createPost(PostDTO postDto,int userId,int categoryId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		
		Post savePost = postRepo.save(post);
		return this.modelMapper.map(savePost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDto, int id) {
		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","post id", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(int id) {
		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","post id", id));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(int pageNumber,int pageSize,String sortBy,String sortDir) {
		
		//implementing pagination
		//for descending
		//Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
	Page<Post> pagePost = this.postRepo.findAll(p);
	
	List<Post> allPosts = pagePost.getContent();
		
		List<PostDTO> postDto = allPosts.stream().map((post) -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDTO getPostById(int id) {
		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","post id", id));
		
		//we can not convert it to stream bcz it works only on list
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(int categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		
		//converting all post to postdto
		List<PostDTO> postDto = posts.stream().map((post) -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public List<PostDTO> getPostByUser(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDTO> postDto = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());;
		
		return postDto;
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		List<Post> findByTitleContaining = this.postRepo.findByTitleContaining(keyword);
		/*
		 * if(findByTitleContaining == null) { throw new
		 * ResourceNotFoundException("Post", "keyword", 0) }
		 * have to create new exception for this
		 * 
		 * 
		 */
		if(findByTitleContaining.isEmpty())
			throw new SearchResultNotFound("Post", keyword);
		
		List<PostDTO> postDtos = findByTitleContaining.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDtos;
	}

}
