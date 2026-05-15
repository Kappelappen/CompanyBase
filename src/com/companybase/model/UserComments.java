package com.companybase.model;

public class UserComments {

	private long id;
	private String comments;
	
	public UserComments() {}
	
	public void setId(long id) {
		
		this.id = id;
		
	}
	
	public void setComments(String comments) {
		
		this.comments = comments;
		
	}
	
	public long getId() { return id; }
	public String getComments() { return comments; }
	
}
