package com.weson.dao;

import com.weson.domian4.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAll();

    Account findByUid(Integer uid);
}
