package com.companybase.dao;
import com.companybase.model.UserComments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CommentsDaoImpl implements CommentsDao {

    private final Connection connection;

    public CommentsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public java.util.List<UserComments> findAll() {
     
    	java.util.List<UserComments> list = new ArrayList<>();
    	
    	String sql = "SELECT * FROM comments";
    	
    	try {
    		
    	Statement stat = connection.createStatement();
    	ResultSet rs = stat.executeQuery(sql);
    	
    	while (rs.next()) {
    		
    		int id = rs.getInt(1);    		
    		String content = rs.getString("content");
    		
    		UserComments comments = new UserComments();
    		comments.setId(id);
    		comments.setComments(content);
    		list.add(comments);
    		
    	}
    	
    	rs.close();
    	stat.close();
    		
    	} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
    	
    	return list;
    	
    }

	@Override
	public void insert(UserComments comments) {
		
		try {
		
		String sql = "INSERT INTO comments " + 
		"(company_id,content) VALUES (?,?)";
		
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setLong(1, comments.getId());
		stat.setString(2, comments.getComments());
		stat.executeUpdate();
		stat.close();
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }			
	}

	@Override
	public UserComments findById(long id) {
		
		UserComments comments = new UserComments();
		
		try {
			
		String sql = "SELECT * FROM comments WHERE company_id = ?";
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setLong(1, id);
		
		ResultSet rs = stat.executeQuery();
		
		if (rs.next()) {
									
			comments.setId(rs.getLong(1));
			comments.setComments(rs.getString("content"));
			
		}
		
		stat.close();
		rs.close();
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
		return comments;
	}

	@Override
	public void update(long tableId, UserComments comments) {
		
		long id = comments.getId();
		
		try {
		
		String sql = "UPDATE comments SET content = ? " + 
		"WHERE company_id = ?";
		
		PreparedStatement stat = connection.prepareStatement(sql);
		stat.setString(1, comments.getComments());
		stat.setLong(2, tableId);
		stat.executeUpdate();
		stat.close();
			
		} catch (java.sql.SQLException ex) { ex.printStackTrace(); }
		
	}
}
