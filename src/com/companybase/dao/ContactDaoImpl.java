package com.companybase.dao;
import com.companybase.model.ApplicationStatus;
import com.companybase.model.Company;
import com.companybase.model.ContactPerson;
import com.companybase.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactDaoImpl implements ContactDao {

    private final Connection connection;

    public ContactDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public java.util.List<ContactPerson> findAll() {
     
    	java.util.List<ContactPerson> list = new ArrayList<>();
    	
    	String sql = "SELECT * FROM contacts";
    	
    	try {
    		
    	Statement stat = connection.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	while (rs.next()) {
    		
    		int id = rs.getInt(1);    		
    		String name = rs.getString("name");    
    		
    		ContactPerson person = new ContactPerson();
    		
    		person.setId(id);
    		person.setName(name);    		
    		
    		list.add(person);
    		
    	}
    	
    	rs.close();
    	stat.close();
    		
    	} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
    	
    	return list;
    	
    }
    
	@Override
	public boolean deleteById(long id) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public long insert(ContactPerson person) {
		
		try {
			
		long id = person.getId();
		String name = person.getName();
		String jobTitle = person.getJobTitle();
		String phone = person.getPhone();
			
		String sql = "INSERT INTO contacts (company_id," + 
		"name,job_title,phone) VALUES (?,?,?,?)";
		
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setLong(1, id);
		stat.setString(2, name);
		stat.setString(3, jobTitle);
		stat.setString(4, phone);
		stat.executeUpdate();
		stat.close();
		
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
		return 0;
	}

	@Override
	public boolean update(ContactPerson person) {
		
		try {
		
		long id = person.getId();
		String name = person.getName();
		String jobTitle = person.getJobTitle();
		String phone = person.getPhone();
		
		String sql = "UPDATE contacts SET name = ?," + 
		"job_title = ?,phone = ? WHERE company_id = ?";
		
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, name);
		stat.setString(2, jobTitle);
		stat.setString(3, phone);
		stat.setLong(4, id);
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }	
				
		return false;
	}

	@Override
	public List<ContactPerson> findById(long id) {
		
		List<ContactPerson> list = new ArrayList<>();
		
		try {
			
		String sql = "SELECT * FROM contacts WHERE company_id = ?";	
			
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
						
			String name = rs.getString("name");
			String jobTitle = rs.getString("job_title");
			String phone = rs.getString("phone");
			ContactPerson person = new ContactPerson();
			person.setId(rs.getLong(1));
			person.setName(name);
			person.setJobTitle(jobTitle);
			person.setPhone(phone);			
			list.add(person);
			
		}
		
		rs.close();
		stmt.close();
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
			
		return list;
	}

	@Override
	public void deleteByCompanyId(long id) {
		
		try {
			
		String sql = "DELETE FROM contacts WHERE company_id = ?";
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setLong(1, id);
		stat.executeUpdate();
		stat.close();
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
		
	}
}
