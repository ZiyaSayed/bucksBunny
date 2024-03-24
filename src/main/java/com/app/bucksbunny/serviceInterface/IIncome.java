package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.Income;
import com.app.bucksbunny.entity.UserIncome;

import java.util.List;

public interface IIncome {

    // POST
    public Income addIncome(Income income, String userEmail);

    // GET
    public Income getIncomeById(int id);

    public List<Income> getAllIncome();

    public List<UserIncome> getIncomeByUser(String userEmail);

    // UPDATE
    public Income updateIncomeById(int id, Income newData);

    // DELETE
    public void deleteIncome(int id);
}
