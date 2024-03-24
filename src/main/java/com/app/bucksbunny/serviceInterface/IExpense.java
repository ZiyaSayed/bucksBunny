package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.Expense;
import com.app.bucksbunny.entity.UserExpense;

import java.util.List;

public interface IExpense {


    // POST
    public Expense addExpense(Expense expense, String userEmail);

    // GET
    public Expense getExpenseById(int id);

    public List<Expense> getAllExpense();

    public List<UserExpense> getExpenseByUser(String userEmail);

    // UPDATE
    public Expense updateExpenseById(int id, Expense newData);

    // DELETE
    public void deleteExpense(int id);
}
