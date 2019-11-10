package com.weson.dao;

import com.weson.domian4.User;

import java.util.List;

public interface UserDao {

    List<User> findAllaa();


    User findById(Integer userId);
}
