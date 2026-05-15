package com.companybase.dao;
import com.companybase.model.ApplicationStatus;
import com.companybase.model.Company;
import com.companybase.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CompanyDaoImpl implements CompanyDao {

    private final Connection connection;

    public CompanyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public java.util.List<Company> findAll() {
     
    	java.util.List<Company> list = new ArrayList<>();
    	
    	String sql = "SELECT * FROM companies";
    	
    	try {
    		
    	Statement stat = connection.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	while (rs.next()) {
    		
    		int id = rs.getInt(1);    		
    		String name = rs.getString("company_name");    
    		String industry = rs.getString("industry");
    		String streetAddress = rs.getString("street_address");
    		String city = rs.getString("city");
    		String country = rs.getString("country");
    		String website = rs.getString("website");
    		String email = rs.getString("email");
    		String phone = rs.getString("phone");
    		String appDate = rs.getString("application_date");
    		String appStatus = rs.getString("application_status");
    		Company company = new Company();
    		
    		company.setId(id);
    		company.setCompanyName(name);
    		company.setIndustry(industry);
    		company.setStreetAddress(streetAddress);
    		company.setCity(city);
    		company.setCountry(country);
    		company.setWebsite(website);
    		company.setEmail(email);
    		company.setPhone(phone);
    		company.setApplicationDate(appDate);
    		company.setApplicationStatus(appStatus);
    		
    		list.add(company);
    		
    	}
    	
    	rs.close();
    	stat.close();
    		
    	} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
    	
    	return list;
    	
    }
    
	@Override
	public void insert(Company company) {
		
    	String sql = "INSERT INTO companies (company_name,industry," + 
    	"street_address,city,country,website,email,phone,application_date," +
    	"application_status) VALUES (?,?,?,?,?,?,?,?,?,?)"; 
    	
		try {
		
		PreparedStatement stmt =
			connection.prepareStatement(sql, 
			Statement.RETURN_GENERATED_KEYS);
			
		stmt.setString(1, company.getName());
	    stmt.setString(2, company.getIndustry());
	    stmt.setString(3, company.getStreetAddress());
	    stmt.setString(4, company.getCity());
	    stmt.setString(5, company.getCountry());
	    stmt.setString(6, company.getWebsite());
	    stmt.setString(7, company.getEmail());
	    stmt.setString(8, company.getPhone());
	    stmt.setString(9, company.getApplicationDate());
	    stmt.setString(10, company.getApplicationStatus());
	    
	    stmt.executeUpdate();
			
	    ResultSet keys = stmt.getGeneratedKeys();
	    
	    if (keys.next()) {
	    	
            long id = keys.getLong(1);
            company.setId((int) id);
            
        }
	    
	    stmt.close();
	    keys.close();
	    
		} catch (SQLException ex) { ex.printStackTrace(); }		
		
	}

	@Override
	public boolean update(long id, Company company) {

	    String sql =
	    	"UPDATE companies SET " +
	        "company_name = ?, " +
	        "industry = ?, " +
	        "street_address = ?, " +
	        "city = ?, " +
	        "country = ?, " +
	        "website = ?, " +
	        "email = ?, " +
	        "phone = ?, " +
	        "application_date = ?, " +
	        "application_status = ? " +
	        "WHERE id = ?";

	    try (PreparedStatement stat =
	                 connection.prepareStatement(sql)) {

	        stat.setString(1, company.getName());
	        stat.setString(2, company.getIndustry());
	        stat.setString(3, company.getStreetAddress());
	        stat.setString(4, company.getCity());
	        stat.setString(5, company.getCountry());
	        stat.setString(6, company.getWebsite());
	        stat.setString(7, company.getEmail());
	        stat.setString(8, company.getPhone());
	        stat.setString(9, company.getApplicationDate());
	        stat.setString(10, company.getApplicationStatus());
	        stat.setLong(11, id);

	        int rowsUpdated = stat.executeUpdate();
	        
	        stat.close();

	        // true if at least one row was updated
	        return rowsUpdated > 0;

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean deleteById(long id) {
		
		try {
			
		String sql = "DELETE FROM companies WHERE id = ?";
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setLong(1, id);
		
		stat.executeUpdate();
		stat.close();
		
		} catch (SQLException ex) { ex.printStackTrace(); }		
		
		return true;
	
	}

	@Override
	public Company findById(long id) {
		
		try {
			
		String sql = "SELECT * FROM companies WHERE id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		
		ResultSet rs = stmt.executeQuery();		
		
		if (rs.next()) {
			
			Company company = new Company();
			
			String name = rs.getString("company_name");
			String industry = rs.getString("industry");
			String city = rs.getString("city");
			String address = rs.getString("street_address");
			String country = rs.getString("country");
			String website = rs.getString("website");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String appDate = rs.getString("application_date");
			String appStatus = rs.getString("application_status");
						
			company.setId(id);
			company.setCompanyName(name);
			company.setIndustry(industry);
			company.setCity(city);
			company.setStreetAddress(address);
			company.setCountry(country);
			company.setWebsite(website);
			company.setEmail(email);
			company.setPhone(phone);
			company.setApplicationDate(appDate);
			company.setApplicationStatus(appStatus);
						
			return company;
			
		}
		
		rs.close();
		stmt.close();
			
		} catch (SQLException ex) { ex.printStackTrace(); }
		
		return null;
		
	}

	public Company getLastInsertedCompany() {

	    String sql = "SELECT * FROM companies " + 
	    "ORDER BY id DESC LIMIT 1";
	            
	    try {
	            
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {

	        	Company company = new Company();

	            company.setId(rs.getInt("id"));
	            company.setCompanyName(rs.getString("company_name"));
	            company.setIndustry(rs.getString("industry"));
	            company.setStreetAddress(rs.getString("street_address"));
	            company.setCity(rs.getString("city"));
	            company.setCountry(rs.getString("country"));
	            company.setWebsite(rs.getString("website"));
	            company.setEmail(rs.getString("email"));
	            company.setPhone(rs.getString("phone"));
	            company.setApplicationDate(rs.getString("application_date"));
	            company.setApplicationStatus(rs.getString("application_status"));

	            return company;
	            
	        }
	        
	        rs.close();
	        stmt.close();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return null;
	
	}

	@Override
	public java.util.List<Company> search(String text) {
	
		String sql =
		        "SELECT * FROM companies " +
		        "WHERE company_name LIKE ? " +
		        "   OR industry LIKE ? " +
		        "   OR city LIKE ? " +
		        "   OR country LIKE ? " +
		        "ORDER BY company_name";

		java.util.List<Company> list = new ArrayList<>();
		String pattern = "%" + text.trim() + "%";
		
		try {
			
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, pattern);
		stat.setString(2, pattern);
		stat.setString(3, pattern);
		
		ResultSet rs = stat.executeQuery();
		
		if (rs.next()) {
			
			 Company company = new Company();
			 
             company.setId(rs.getInt("id"));			
             company.setCompanyName(rs.getString("company_name"));
             company.setIndustry(rs.getString("email"));
             company.setCity(rs.getString("phone"));;
             list.add(company);             
             
		}
		
		rs.close();
		stat.close();
		
		} catch (SQLException ex) { ex.printStackTrace(); }
		
		return list;
	
	}	
}
