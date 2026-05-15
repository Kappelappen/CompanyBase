package com.companybase.model;

public class ApplicationStatus {

    private long id;
    private String name;
    
    public ApplicationStatus() {}
    
    public ApplicationStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public void setId(long id) {
    	
    	this.id = id;
    	
    }
    
    public void setName(String name) {
    	
    	this.name = name;
    	
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}