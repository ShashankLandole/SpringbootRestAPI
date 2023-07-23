package com.blog.service;

import java.util.List;

import com.blog.payloads.CategoryDTO;

public interface CategoryService {
	
CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	CategoryDTO updateCategory(CategoryDTO categoryDTO,int categoryId);
	
	 void deleteCategory(int categoryId);
	
	CategoryDTO getCategory(int categoryId);
	
	List<CategoryDTO> getAllCategory();

}
