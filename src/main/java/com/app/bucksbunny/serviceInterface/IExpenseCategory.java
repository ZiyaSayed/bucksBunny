package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.Budget;
import com.app.bucksbunny.entity.ExpenseCategory;
import com.app.bucksbunny.entity.ExpenseCategoryMapping;
import com.app.bucksbunny.request.UpdateBudgetBody;
import com.app.bucksbunny.request.UpdateCategoryBody;

import java.util.List;

public interface IExpenseCategory {

    // POST
    public ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory, String userEmail);

    // GET
    public ExpenseCategory getExpenseCategoryById(int id);

    public List<ExpenseCategory> getAllExpenseCategory();

    public List<ExpenseCategoryMapping> getExpenseCategoryByUser(String userEmail);

    // UPDATE
    public ExpenseCategory updateExpenseCategoryById(int id, UpdateCategoryBody newData);

    // DELETE
    public void deleteExpenseCategory(int id);

    // =========== Budget services ===========

    // POST
    public Budget addBudget( Budget newBudget);

    // GET
    public List<Budget> getBudgetByMonth(int month);

    public Budget getBudgetById(int id);

    // UPDATE
    public Budget updateBudgetById(int budgetId, UpdateBudgetBody limit);

    // DELETE
    public void deleteBudgetById(int budgetId);
}
