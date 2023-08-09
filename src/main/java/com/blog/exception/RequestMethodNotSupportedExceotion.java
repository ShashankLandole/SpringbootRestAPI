package com.blog.exception;

public class RequestMethodNotSupportedExceotion extends RuntimeException {
	
	String errorname;

	public RequestMethodNotSupportedExceotion(String errorname) {
		super(String.format("please check HTTP Method and try again " +errorname));
		this.errorname = errorname;
	}

	public String getErrorname() {
		return errorname;
	}

	public void setErrorname(String errorname) {
		this.errorname = errorname;
	}
	
	
	
	
	
}
