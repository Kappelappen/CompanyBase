package com.companybase.dao;

import java.util.List;
import com.companybase.model.Details;

public interface DetailsDao {

    void insert(Details details);
    boolean update(Details details);
    boolean deleteById(long id);
    Details findById(long id);
    List<Details> findAll();
    int count();

}
