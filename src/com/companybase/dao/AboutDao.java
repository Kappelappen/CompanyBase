package com.companybase.dao;

import java.util.List;

import com.companybase.model.AboutText;

public interface AboutDao {

	void insert(AboutText details);
    boolean update(AboutText details);
    boolean deleteById(long id);
    AboutText findById(long id);
    List<AboutText> findAll();
    	
}
