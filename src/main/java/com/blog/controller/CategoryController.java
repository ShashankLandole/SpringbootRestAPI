package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.APIResponse;
import com.blog.payloads.CategoryDTO;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDto){
		
		CategoryDTO createCategory = categoryService.createCategory(categoryDto);
		return new  ResponseEntity<CategoryDTO>(createCategory,HttpStatus.CREATED);
		
		
	}
	
	
	//update
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDto, @PathVariable Integer id){
		
		CategoryDTO updateCategory = categoryService.updateCategory(categoryDto, id);
		
		return new  ResponseEntity<CategoryDTO>(updateCategory,HttpStatus.OK);
		
		
	}
	
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse> deleteCategory(@PathVariable Integer id){
		
		 categoryService.deleteCategory(id);
		
		return new  ResponseEntity<APIResponse>(new APIResponse("category is successfully deleted", true),HttpStatus.OK);
		
		
	}
	
	
	//get
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id){
		
		 CategoryDTO category = categoryService.getCategory(id);
		
		return new  ResponseEntity<CategoryDTO>(category,HttpStatus.OK);
		
		
	}
	
	
	
	//getall
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
		
		  List<CategoryDTO> allCategory = categoryService.getAllCategory();
		
		return new  ResponseEntity<List<CategoryDTO>>(allCategory,HttpStatus.OK);
		
		
	}
	
	
	
}
