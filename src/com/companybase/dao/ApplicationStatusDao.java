package com.companybase.dao;
import java.util.List;

import com.companybase.model.ApplicationStatus;

public interface ApplicationStatusDao {

    List<ApplicationStatus> findAll();
    void insert(ApplicationStatus status);

}
