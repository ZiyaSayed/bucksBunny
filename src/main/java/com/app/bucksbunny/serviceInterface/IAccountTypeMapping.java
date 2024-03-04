package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.AccountTypeMapping;

import java.util.List;

public interface IAccountTypeMapping {


    // ========= GET =========
    public List<AccountTypeMapping> getAccountTypeByUser(String userEmail);
}
