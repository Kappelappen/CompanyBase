package com.companybase.dao;
import com.companybase.model.ApplicationStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ApplicationStatusDaoImpl implements ApplicationStatusDao {

    private final Connection connection;

    public ApplicationStatusDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public java.util.List<ApplicationStatus> findAll() {
     
    	java.util.List<ApplicationStatus> list = new ArrayList<>();
    	
    	String sql = "SELECT * FROM application_status";
    	
    	try {
    		
    	Statement stat = connection.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	while (rs.next()) {
    		
    		int id = rs.getInt(1);    		
    		String name = rs.getString("name");
    		
    		ApplicationStatus status = new ApplicationStatus(id,name);
    		list.add(status);
    		
    	}
    	
    	rs.close();
    	stat.close();
    		
    	} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
    	
    	return list;
    	
    }

	@Override
	public void insert(ApplicationStatus status) {
		
		long id = status.getId();
		String name = status.getName();
		
	}
}
