package com.app.bucksbunny.service;


import com.app.bucksbunny.entity.IncomeCategory;
import com.app.bucksbunny.exceptions.ResourceNotFoundException;
import com.app.bucksbunny.repository.IncomeCategoryRepository;
import com.app.bucksbunny.request.UpdateCategoryBody;
import com.app.bucksbunny.serviceInterface.IIncomeCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeCategoryService implements IIncomeCategory {

    @Autowired
    private IncomeCategoryRepository repo;

    @Autowired
    private UserService userService;

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

        throw new ResourceNotFoundException();
    }

    @Override
    public List<IncomeCategory> getAllIncomeCategory() {

        List<IncomeCategory> categories = repo.findAll();
        return categories;
    }

    @Override
    public IncomeCategory getIncomeCategoryByUser() {
        return null;
    }

    @Override
    public IncomeCategory updateIncomeCategoryById(int id, UpdateCategoryBody newData) {

        // get the category by id
        Optional<IncomeCategory> incomeCategory = repo.findById(id);

        if(incomeCategory.isPresent()){

            IncomeCategory prevCategory = incomeCategory.get();

            if(newData.getName() != null){
                prevCategory.setName(newData.getName());
            }
            if(newData.getIcon() != null){
                prevCategory.setIcon(newData.getIcon());
            }

            return repo.save(prevCategory);
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteIncomeCategory(int id) {
        Optional<IncomeCategory> entry = repo.findById(id);

        if(entry.isPresent()){
            repo.deleteById(id);

            return;
        }

        throw new ResourceNotFoundException();

    }
}
