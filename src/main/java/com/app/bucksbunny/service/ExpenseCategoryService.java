package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.Budget;
import com.app.bucksbunny.entity.ExpenseCategory;
import com.app.bucksbunny.entity.ExpenseCategoryMapping;
import com.app.bucksbunny.exceptions.ResourceNotFoundException;
import com.app.bucksbunny.repository.BudgetRepository;
import com.app.bucksbunny.repository.ExpenseCategoryMappingRepository;
import com.app.bucksbunny.repository.ExpenseCategoryRepository;
import com.app.bucksbunny.request.UpdateBudgetBody;
import com.app.bucksbunny.request.UpdateCategoryBody;
import com.app.bucksbunny.serviceInterface.IExpenseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseCategoryService implements IExpenseCategory {

    @Autowired
    private ExpenseCategoryRepository repo;

    @Autowired
    private ExpenseCategoryMappingRepository mappingRepo;

    @Autowired
    private BudgetRepository budgetRepo;

    @Override
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory, String userEmail) {

        // creating new expense category
        ExpenseCategory newCategory = repo.save(expenseCategory);

        // mapping it to the user expense category(mapping) table
        ExpenseCategoryMapping newExpenseMapping = new ExpenseCategoryMapping();
        newExpenseMapping.setUserId(userEmail);
        newExpenseMapping.setExpenseCategory(newCategory);

        mappingRepo.save(newExpenseMapping);

        return newCategory;
    }

    @Override
    public ExpenseCategory getExpenseCategoryById(int id) {

        Optional<ExpenseCategory> entry = repo.findById(id);

        if(entry.isPresent()){
            ExpenseCategory expenseCategory = entry.get();
            return  expenseCategory;
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public List<ExpenseCategory> getAllExpenseCategory() {

        List<ExpenseCategory> categories = repo.findAll();
        return categories;
    }

    @Override
    public List<ExpenseCategoryMapping> getExpenseCategoryByUser(String userEmail) {

        List<ExpenseCategoryMapping> userCategories = mappingRepo.findAllByUserId(userEmail);

        return userCategories;
    }


    @Override
    public ExpenseCategory updateExpenseCategoryById(int id, UpdateCategoryBody newData) {

        // get the category by id
        Optional<ExpenseCategory> expenseCategory = repo.findById(id);

        if(expenseCategory.isPresent()){

            ExpenseCategory prevCategory = expenseCategory.get();

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
    public void deleteExpenseCategory(int id) {

        Optional<ExpenseCategory> entry = repo.findById(id);

        if(entry.isPresent()){
            repo.deleteById(id);

            return;
        }

        throw new ResourceNotFoundException();
    }

    // =========== Budget services ===========

    @Override
    public Budget addBudget(Budget newBudget) {

        return budgetRepo.save(newBudget);

    }

    // get by month

    @Override
    public Budget getBudgetById(int id) {

        Optional<Budget> entry = budgetRepo.findById(id);

        if(entry.isPresent()){
            Budget budget = entry.get();
            return  budget;
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public List<Budget> getBudgetByMonth(int id) {
        return null;
    }

    @Override
    public Budget updateBudgetById(int budgetId, UpdateBudgetBody updateBody) {

        Optional<Budget> entry = budgetRepo.findById(budgetId);

        if(entry.isPresent()){

            Budget prevBudget = entry.get();

            prevBudget.setBudgetLimit(updateBody.getLimit());

            return budgetRepo.save(prevBudget);
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteBudgetById(int budgetId) {

    }
}
