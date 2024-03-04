package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.AccountType;
import com.app.bucksbunny.entity.AccountTypeMapping;
import com.app.bucksbunny.exceptions.ResourceNotFoundException;
import com.app.bucksbunny.repository.AccountTypeMappingRepository;
import com.app.bucksbunny.repository.AccountTypeRepository;
import com.app.bucksbunny.serviceInterface.IAccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeService implements IAccountType {

    @Autowired
    private AccountTypeRepository repo;

    @Autowired
    private AccountTypeMappingRepository mappingRepo;

    @Override
    public AccountType addAccountType(AccountType accountType, String userEmail) {

        // creating new account category
        AccountType newAccountType = repo.save(accountType);

        // mapping it to the user account type
        AccountTypeMapping newAccountMapping = new AccountTypeMapping();
        newAccountMapping.setUserId(userEmail);
        newAccountMapping.setAccountType(newAccountType);

        mappingRepo.save(newAccountMapping);

        return newAccountType;
    }

    @Override
    public AccountType getAccountTypeById(int id) {

        Optional<AccountType> entry = repo.findById(id);

        if(entry.isPresent()){
            AccountType accountType = entry.get();
            return accountType;
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public List<AccountType> getAllAccountType() {

        List<AccountType> accountTypes = repo.findAll();
        return accountTypes;
    }

    @Override
    public AccountType updateAccountType(int id, AccountType newData) {

        // get the account by id
        Optional<AccountType> accountType = repo.findById(id);

        if(accountType.isPresent()){

            AccountType prevAccountType = accountType.get();

            if(newData.getName() != null){
                prevAccountType.setName(newData.getName());
            }
            if(newData.getIcon() != null){
                prevAccountType.setIcon(newData.getIcon());
            }

            return repo.save(prevAccountType);
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteAccountType(int id) {

        Optional<AccountType> entry = repo.findById(id);

        if(entry.isPresent()){
            repo.deleteById(id);

            return ;
        }

        throw new ResourceNotFoundException();
    }
}
