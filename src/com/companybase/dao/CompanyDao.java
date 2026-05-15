package com.companybase.dao;

import java.util.List;

import com.companybase.model.Company;

public interface CompanyDao {

    public void insert(Company company);
    boolean update(long id, Company company);
    boolean deleteById(long id);
    public Company findById(long id);
    List<Company> findAll();
    public java.util.List<Company> search(String text);
    public Company getLastInsertedCompany();
    
}
