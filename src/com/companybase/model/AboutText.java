package com.companybase.model;

public class AboutText {

	private long id;
	private String text;
	
	public AboutText() {}
	
	public void setAboutText(String text) {
		
		this.text = text;
		
	}
	
	public void setId(long id) {
		
		this.id = id;
		
	}
	
	public long getId() { return id; }
	public String getAboutText() { return text; }
	
}
