package com.companybase.dao;
import com.companybase.model.ApplicationStatus;
import com.companybase.model.Company;
import com.companybase.model.Country;
import com.companybase.model.Details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailsDaoImpl implements DetailsDao {

    private final Connection connection;

    public DetailsDaoImpl(Connection connection) {
        
    	this.connection = connection;
        
    }

    @Override
    public java.util.List<Details> findAll() {
     
    	java.util.List<Details> list = new ArrayList<>();
    	
    	String sql = "SELECT * FROM details";
    	
    	try {
    		
    	Statement stat = connection.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	while (rs.next()) {
    		
    		int id = rs.getInt(1);    		    		
    		Details details = new Details();
    		details.setId(id);
    		list.add(details);
    		
    	}
    	
    	rs.close();
    	stat.close();
    		
    	} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
    	
    	return list;
    	
    }
    
	@Override
	public void insert(Details details) {
		
		try {
		
		String sql = "INSERT INTO details (company_id, " + 
		"org_number,employee_count,founded_year," + 
		"linkedin_address,facebook_address) " + 
		"VALUES (?,?,?,?,?,?)";
			
		PreparedStatement stmt =
			connection.prepareStatement(sql);
			
		stmt.setLong(1, details.getId());
		stmt.setString(2, details.getOrgNumber());
	    stmt.setString(3, details.getEmployeeCount());
	    stmt.setString(4, details.getFoundedYear());
	    stmt.setString(5, details.getLinkedinAddress());
	    stmt.setString(6, details.getFacebookAddress());
	    
	    stmt.executeUpdate();			    
	    stmt.close();
	    
		} catch (SQLException ex) { ex.printStackTrace(); }				
	}

	@Override
	public boolean update(Details details) {
		
		long id = details.getId();
		
		String organizationNumber = details.getOrgNumber();
		String employeeCount = details.getEmployeeCount();
		String foundedYear = details.getFoundedYear();
		String linkedin = details.getLinkedinAddress();
		String facebook = details.getFacebookAddress();
				
		try {
			
		String sql = "UPDATE details SET org_number = ?," + 
		"employee_count = ?,founded_year = ?,linkedin_address = ?," + 
		"facebook_address = ? WHERE company_id = ?";
		
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, organizationNumber);
		stat.setString(2,  employeeCount);
		stat.setString(3, foundedYear);
		stat.setString(4, linkedin);
		stat.setString(5, facebook);
		stat.setLong(6, id);
		
		stat.executeUpdate();
		stat.close();
		
		} catch (SQLException ex) { ex.printStackTrace(); }	
				
		return false;
	}

	@Override
	public boolean deleteById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Details findById(long id) {
			
		Details details = new Details();
		String sql ="SELECT * FROM details WHERE company_id = ?";
		
		try {
			
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			
			String orgNumber = rs.getString("org_number");
			String employeeCount = rs.getString("employee_count");
			String foundedYear = rs.getString("founded_year");
			String linkedinAddress = rs.getString("linkedin_address");
			String facebookAddress = rs.getString("facebook_address");
		
			details.setOrgNumber(orgNumber);
			details.setEmployeeCount(employeeCount);
			details.setFoundedYear(foundedYear);
			details.setLinkedinAddress(linkedinAddress);
			details.setFacebookAddress(facebookAddress);
			
		}
		
		rs.close();
		stmt.close();
		
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
		return details;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
}
