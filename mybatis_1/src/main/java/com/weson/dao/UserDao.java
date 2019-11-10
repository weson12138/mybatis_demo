package com.weson.dao;

import com.weson.domian2.QueryVo;
import com.weson.domian2.User;

import java.util.List;

public interface UserDao {

    List<User> findByCoundition(User user);

    List<User> findUserInIds(QueryVo vo);
}
