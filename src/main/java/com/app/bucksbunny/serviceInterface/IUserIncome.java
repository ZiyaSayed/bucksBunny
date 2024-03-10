package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.UserIncome;

import java.util.List;

public interface IUserIncome {

    // GET
    public List<UserIncome> getIncomeByUser(String userEmail);
}
