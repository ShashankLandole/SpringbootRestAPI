package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.payloads.CategoryDTO;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
	
	
	
	

}
