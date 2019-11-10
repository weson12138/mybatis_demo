package com.weson.dao;

import com.weson.domian2.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();
}
