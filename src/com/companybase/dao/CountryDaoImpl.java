package com.companybase.dao;
import com.companybase.model.ApplicationStatus;
import com.companybase.model.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CountryDaoImpl implements CountryDao {

    private final Connection connection;

    public CountryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public java.util.List<Country> findAll() {
     
    	java.util.List<Country> list = new ArrayList<>();
    	
    	String sql = "SELECT * FROM Country";
    	
    	try {
    		
    	Statement stat = connection.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	while (rs.next()) {
    		
    		int id = rs.getInt(1);    		
    		String name = rs.getString("name");
    		
    		Country country = new Country(id,name);
    		list.add(country);
    		
    	}
    	
    	rs.close();
    	stat.close();
    		
    	} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
    	
    	return list;
    	
    }
}
