package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.Income;
import com.app.bucksbunny.entity.UserIncome;
import com.app.bucksbunny.exceptions.ResourceNotFoundException;
import com.app.bucksbunny.repository.IncomeRepository;
import com.app.bucksbunny.repository.UserIncomeRepository;
import com.app.bucksbunny.serviceInterface.IIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService implements IIncome {

    @Autowired
    private IncomeRepository repo;

    @Autowired
    private UserIncomeRepository mappingRepo;

    @Override
    public Income addIncome(Income income, String userEmail) {

        // checking if income have date & time
        if(income.getDate() == null){
            income.setDate(LocalDate.now());
        }
        if(income.getTime() == null){
            income.setTime(LocalTime.now());
        }

        // creating new income
        Income newIncome = repo.save(income);

        // mapping it to the user
        UserIncome newUserIncome = new UserIncome();
        newUserIncome.setUserId(userEmail);
        newUserIncome.setIncome(newIncome);

        mappingRepo.save(newUserIncome);

        return newIncome;
    }

    @Override
    public Income getIncomeById(int id) {

        Optional<Income> entry = repo.findById(id);

        if(entry.isPresent()){
            Income income = entry.get();

            return income;
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public List<Income> getAllIncome() {

        List<Income> incomes = repo.findAll();
        return incomes;
    }

    @Override
    public Income updateIncomeById(int id, Income newData) {

        // get the income by id
        Optional<Income> entry = repo.findById(id);

        if(entry.isPresent()){

            Income prevIncome = entry.get();

            System.out.println(newData.getAmount());

            if(newData.getAmount() != 0){
                prevIncome.setAmount(newData.getAmount());
            }
            if(newData.getCategory() != null){
                prevIncome.setCategory(newData.getCategory());
            }
            if(newData.getAccountType() != null){
                prevIncome.setAccountType(newData.getAccountType());
            }
            if(newData.getNotes() != null){
                prevIncome.setNotes(newData.getNotes());
            }
            if(newData.getDate() != null){
                prevIncome.setDate(newData.getDate());
            }
            if(newData.getTime() != null){
                prevIncome.setTime(newData.getTime());
            }

            return repo.save(prevIncome);
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteIncome(int id) {

        // getting the entry by id
        Optional<Income> entry = repo.findById(id);

        if(entry.isPresent()){

            repo.deleteById(id);
            return ;
        }

        throw new ResourceNotFoundException();

    }

    @Override
    public List<UserIncome> getIncomeByUser(String userEmail) {

        List<UserIncome> userIncomes = mappingRepo.findAllByUserId(userEmail);

        return userIncomes;
    }
}
