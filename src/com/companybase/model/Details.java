package com.companybase.model;

public class Details {

	private long id;
	private String orgNumber;
	private String employeeCount;
	private String foundedYear;
	private String linkedinAddress;
	private String facebookAddress;
	
	public Details() {}
	
	public void setId(long id) {
		
		this.id = id;
		
	}
	
	public void setOrgNumber(String number) {
		
		this.orgNumber = number;
				
	}
	
	public void setEmployeeCount(String count) {
		
		this.employeeCount = count;
		
	}
	
	public void setFoundedYear(String year) {
		
		this.foundedYear = year;
		
	}
	
	public void setLinkedinAddress(String address) {
		
		this.linkedinAddress = address;
		
	}
	
	public void setFacebookAddress(String facebook) {
		
		this.facebookAddress = facebook;
		
	}
	
	public long getId() { return id; }
	public String getOrgNumber() { return orgNumber; }
	public String getEmployeeCount() { return employeeCount; }
	public String getFoundedYear() { return foundedYear; }
	public String getLinkedinAddress() { return linkedinAddress; }
	public String getFacebookAddress() { return facebookAddress; }
	
}
