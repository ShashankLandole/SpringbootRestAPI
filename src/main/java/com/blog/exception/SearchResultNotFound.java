package com.blog.exception;

public class SearchResultNotFound extends RuntimeException{
	
	String resourceName;
	String search;
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}

	public SearchResultNotFound(String resourceName, String search) {
		super(String.format("%s not found for %s",resourceName,search));
		this.resourceName = resourceName;
		this.search = search;
	}
	
	
	
	
	

}
