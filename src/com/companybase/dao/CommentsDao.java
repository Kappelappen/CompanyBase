package com.companybase.dao;
import java.util.List;

import com.companybase.model.UserComments;

public interface CommentsDao {

    List<UserComments> findAll();
    UserComments findById(long id);
    void update(long tableId,UserComments comments);
    void insert(UserComments comments);

}
