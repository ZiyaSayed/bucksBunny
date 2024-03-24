package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.AccountType;
import com.app.bucksbunny.entity.AccountTypeMapping;

import java.util.List;

public interface IAccountType {

    // ========= POST =========
    public AccountType addAccountType(AccountType accountType, String userEmail);

    // GET
    public AccountType getAccountTypeById(int id);

    public List<AccountType> getAllAccountType();

    public List<AccountTypeMapping> getAccountTypeByUser(String userEmail);

    // UPDATE
    public AccountType updateAccountType(int id, AccountType newData);

    // DELETE
    public void deleteAccountType(int id);
}
