package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.AccountTypeMapping;
import com.app.bucksbunny.exceptions.UserNotFoundException;
import com.app.bucksbunny.repository.AccountTypeMappingRepository;
import com.app.bucksbunny.serviceInterface.IAccountTypeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeMappingService implements IAccountTypeMapping {

    @Autowired
    private AccountTypeMappingRepository repo;

    @Override
    public List<AccountTypeMapping> getAccountTypeByUser(String userEmail) throws UserNotFoundException {

        List<AccountTypeMapping> userAccounts = repo.findAllByUserId(userEmail);

        return userAccounts;

    }
}
