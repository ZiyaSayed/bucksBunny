package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.IncomeCategory;
import com.app.bucksbunny.entity.IncomeCategoryMapping;
import com.app.bucksbunny.request.UpdateCategoryBody;

import java.util.List;

public interface IIncomeCategory {

    // POST
    public IncomeCategory addIncomeCategory(IncomeCategory incomeCategory, String userEmail);

    // GET
    public IncomeCategory getIncomeCategoryById(int id);

    public List<IncomeCategory> getAllIncomeCategory();

    public List<IncomeCategoryMapping> getIncomeCategoryByUser(String userEmail);

    // UPDATE
    public IncomeCategory updateIncomeCategoryById(int id, UpdateCategoryBody newData);

    // DELETE
    public void deleteIncomeCategory(int id);
}
