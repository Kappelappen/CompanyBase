package com.companybase.dao;

import java.util.List;
import com.companybase.model.ContactPerson;

public interface ContactDao {

	void deleteByCompanyId(long id);
    long insert(ContactPerson company);
    boolean update(ContactPerson company);
    boolean deleteById(long id);
    List<ContactPerson> findById(long id);
    List<ContactPerson> findAll();
    
}
