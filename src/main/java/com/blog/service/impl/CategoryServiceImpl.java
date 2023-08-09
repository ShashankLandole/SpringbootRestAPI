package com.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.CategoryDTO;
import com.blog.repositories.CategoryRepo;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		
		//converting from dto to normal class
		Category category = this.modelMapper.map(categoryDTO, Category.class);
		
		Category addedCategory = categoryRepo.save(category);
		
		return this.modelMapper.map(addedCategory,categoryDTO.getClass());
		
		
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId) {
		
		
		
		
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category Id",categoryId));
		
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		
		Category saveCatgeory = categoryRepo.save(category);
		
		
		
		return this.modelMapper.map(saveCatgeory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category Id",categoryId));
		
		categoryRepo.deleteById(categoryId);
		
	}

	@Override
	public CategoryDTO getCategory(int categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category Id",categoryId));
		
		return this.modelMapper.map(category,CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> allCategory = this.categoryRepo.findAll();
		
		//now we need to change c=from category to dto
		List<CategoryDTO> catDtos = allCategory.stream().map((cat)->this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		
		
		return catDtos;
	}
	
	

}
