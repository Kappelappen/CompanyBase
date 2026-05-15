package com.companybase.dao;
import com.companybase.model.AboutText;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AboutDaoImpl implements AboutDao {

    private final Connection connection;

    public AboutDaoImpl(Connection connection) {
        
    	this.connection = connection;
        
    }

    @Override
    public java.util.List<AboutText> findAll() {
     
    	java.util.List<AboutText> list = new ArrayList<>();
    	
    	String sql = "SELECT * FROM about_text";
    	
    	try {
    		
    	Statement stat = connection.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	while (rs.next()) {
    		
    		int id = rs.getInt(1);    		
    		String story = rs.getString("story");    
    		
    		AboutText text = new AboutText();
    		text.setId(id);
    		text.setAboutText(story);
    		
    	}
    	
    	rs.close();
    	stat.close();
    		
    	} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
    	
    	return list;
    	
    }
    
	@Override
	public void insert(AboutText text) {
		
		try {
		
		String sql = "INSERT INTO about_text (company_id, " + 
		"story) VALUES (?,?)";
			
		PreparedStatement stmt =
			connection.prepareStatement(sql);
			
		stmt.setLong(1, text.getId());
		stmt.setString(2, text.getAboutText());
	    
	    stmt.executeUpdate();			    
	    stmt.close();
	    
		} catch (SQLException ex) { ex.printStackTrace(); }				
	}

	@Override
	public boolean update(AboutText text) {
		
		long id = text.getId();
		
		try {
			
		String sql = "UPDATE about_text " + 
		"SET story = ? WHERE company_id = ?";
		
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, text.getAboutText());
		stat.setLong(2, id);
		
		stat.executeUpdate();
		stat.close();
		
		
		} catch (SQLException ex) { ex.printStackTrace(); }
		
		return true;
	}

	@Override
	public boolean deleteById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AboutText findById(long id) {
					
		AboutText about = new AboutText();
		
		try {
			
		String sql = "SELECT * FROM about_text WHERE company_id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1,id);
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			
			long numValue = rs.getLong(1);
			String text = rs.getString("story");
			
			about.setId(numValue);
			about.setAboutText(text);
			
		}
		
		stmt.close();
		rs.close();
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
		return about;
	}
}
