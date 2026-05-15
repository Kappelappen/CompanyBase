package com.companybase.model;

public class Person {

	private int id;
	private String name;
	private String jobTitle;
	private String phone;
	
	public Person() {}
	
	public void setId(int id) {
		
		this.id = id;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public void setJobTitle(String title) {
		
		this.jobTitle = title;
		
	}
	
	public void setPhone(String phone) {
		
		this.phone = phone;
		
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getJobTitle() { return jobTitle; }
	public String getPhone() { return phone; }
	
}
