package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.Expense;
import com.app.bucksbunny.entity.UserExpense;
import com.app.bucksbunny.exceptions.ResourceNotFoundException;
import com.app.bucksbunny.repository.ExpenseRepository;
import com.app.bucksbunny.repository.UserExpenseRepository;
import com.app.bucksbunny.serviceInterface.IExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService implements IExpense {

    @Autowired
    private ExpenseRepository repo;

    @Autowired
    private UserExpenseRepository mappingRepo;

    @Override
    public Expense addExpense(Expense expense, String userEmail) {

        // checking if expense have date & time
        if(expense.getDate() == null){
            expense.setDate(LocalDate.now());
        }
        if(expense.getTime() == null){
            expense.setTime(LocalTime.now());
        }

        // creating new expense
        Expense newExpense = repo.save(expense);

        // mapping it to the user
        UserExpense newUserExpense = new UserExpense();
        newUserExpense.setUserId(userEmail);
        newUserExpense.setExpense(newExpense);

        mappingRepo.save(newUserExpense);

        return newExpense;
    }

    @Override
    public Expense getExpenseById(int id) {

        Optional<Expense> entry = repo.findById(id);

        if(entry.isPresent()){
            Expense expense = entry.get();

            return expense;
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public List<Expense> getAllExpense() {

        List<Expense> expenses = repo.findAll();
        return expenses;
    }

    @Override
    public List<UserExpense> getExpenseByUser(String userEmail) {

        List<UserExpense> userExpenses = mappingRepo.findAllByUserId(userEmail);

        return userExpenses;
    }

    @Override
    public Expense updateExpenseById(int id, Expense newData) {

        // get the expense by id
        Optional<Expense> entry = repo.findById(id);

        if(entry.isPresent()){

            Expense prevExpense = entry.get();

            if(newData.getAmount() != 0){
                prevExpense.setAmount(newData.getAmount());
            }
            if(newData.getCategory() != null){
                prevExpense.setCategory(newData.getCategory());
            }
            if(newData.getAccountType() != null){
                prevExpense.setAccountType(newData.getAccountType());
            }
            if(newData.getNotes() != null){
                prevExpense.setNotes(newData.getNotes());
            }
            if(newData.getDate() != null){
                prevExpense.setDate(newData.getDate());
            }
            if(newData.getTime() != null){
                prevExpense.setTime(newData.getTime());
            }

            return repo.save(prevExpense);
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteExpense(int id) {

        // getting the entry by id
        Optional<Expense> entry = repo.findById(id);

        if(entry.isPresent()){

            repo.deleteById(id);
            return ;
        }

        throw new ResourceNotFoundException();
    }
}
