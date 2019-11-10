package com.weson.dao;

import com.weson.domian5.User;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有用户，
     * @return
     */
    List<User> findAll();


    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);
}
