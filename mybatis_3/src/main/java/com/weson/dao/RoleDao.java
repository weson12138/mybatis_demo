package com.weson.dao;

import com.weson.domian.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    List<Role> findAll2();
}
