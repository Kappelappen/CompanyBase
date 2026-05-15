package com.companybase.model;

public class Company {
	
	private long id;
	private String companyName;
	private String industry;
	private String streetAddress;
	private String city;
	private String country;
	private String website;
	private String email;
	private String phone;
	private String applicationDate;
	private String applicationStatus;
	
	public Company() {}
	
	public void setId(long id) {
		
		this.id = id;
		
	}
	
	public void setCompanyName(String name) {
		
		this.companyName = name;
	}
	
	public void setIndustry(String industry) {
		
		this.industry = industry;
		
	}
	
	public void setStreetAddress(String address) {
		
		this.streetAddress = address;
		
	}
	
	public void setCity(String city) {
		
		this.city = city;
		
	}
	
	public void setCountry(String country ) {
		
		this.country = country;
		
	}
	
	public void setWebsite(String website) {
		
		this.website = website;
		
	}
	
	public void setEmail(String email) {
		
		this.email = email;
		
	}
	
	public void setPhone(String phone) {
		
		this.phone = phone;
		
	}
	
	public void setApplicationDate(String date) {
		
		this.applicationDate = date;
		
	}
	
	public void setApplicationStatus(String status) {
		
		this.applicationStatus = status;
		
	}
	
	public long getId() { return id; }
	public String getName() { return companyName; }
	public String getIndustry() { return industry; }
	public String getStreetAddress() { return streetAddress; }
	public String getCity() { return city; }
	public String getCountry() { return country; }
	public String getWebsite() { return website; }
	public String getEmail() { return email; }
	public String getPhone() { return phone; }
	public String getApplicationDate() { return applicationDate; }
	public String getApplicationStatus() { return applicationStatus; }
	
}
