package com.companybase.model;

public class ContactPerson {

	private long id;
	private long companyId;
	private String name;
	private String jobTitle;
	private String phone;
	
	public ContactPerson() {}
		
	public void setId(long id) {
		
		this.id = id;
		
	}
	
	public void setCompanyId(long companyId) {
		
		this.companyId = companyId;
		
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
	
	public long getId() { return id; }
	public long getCompanyId() { return companyId; }
	public String getName() { return name; }
	public String getJobTitle() { return jobTitle; }
	public String getPhone() { return phone; }
	
}
