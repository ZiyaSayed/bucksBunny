package com.app.bucksbunny.service;


import com.app.bucksbunny.entity.IncomeCategory;
import com.app.bucksbunny.exceptions.ResourceNotFoundException;
import com.app.bucksbunny.exceptions.UserNotFoundException;
import com.app.bucksbunny.repository.IncomeCategoryRepository;
import com.app.bucksbunny.serviceInterface.IIncomeCategory;
import com.app.bucksbunny.util.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeCategoryService implements IIncomeCategory {

    @Autowired
    private IncomeCategoryRepository repo;

    @Override
    public IncomeCategory addIncomeCategory(IncomeCategory incomeCategory) {
        return repo.save(incomeCategory);
    }

    @Override
    public IncomeCategory getIncomeCategoryById(int id) {
        Optional<IncomeCategory> entry = repo.findById(id);

        if(entry.isPresent()){
            IncomeCategory incomeCategory = entry.get();
            return  incomeCategory;
        }

        throw new ResourceNotFoundException(ExceptionMessage.ID_NOT_FOUNT);
    }

    @Override
    public List<IncomeCategory> getAllIncomeCategory() {
        return null;
    }

    @Override
    public void deleteIncomeCategory(int id) {
        Optional<IncomeCategory> entry = repo.findById(id);

        if(entry.isPresent()){
            repo.deleteById(id);
        }
        else{
            throw new UserNotFoundException("User does not exist");
        }
    }
}
