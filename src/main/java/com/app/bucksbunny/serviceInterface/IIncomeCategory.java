package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.IncomeCategory;

import java.util.List;

public interface IIncomeCategory {

    public IncomeCategory addIncomeCategory(IncomeCategory incomeCategory);

    public IncomeCategory getIncomeCategoryById(int id);

    public List<IncomeCategory> getAllIncomeCategory();

    public void deleteIncomeCategory(int id);
}
