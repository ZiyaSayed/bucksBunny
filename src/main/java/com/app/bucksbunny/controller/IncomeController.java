package com.app.bucksbunny.controller;


import com.app.bucksbunny.entity.Income;
import com.app.bucksbunny.entity.UserIncome;
import com.app.bucksbunny.response.APIResponse;
import com.app.bucksbunny.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService service;


    @PostMapping("/new")
    public ResponseEntity<APIResponse> addIncome(@RequestBody Income income, @AuthenticationPrincipal UserDetails userDetails){

        Income newIncome = service.addIncome(income, userDetails.getUsername());

        APIResponse response = new APIResponse("", true, newIncome);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse> getList(){

        List<Income> incomes = service.getAllIncome();

        APIResponse response = new APIResponse("", true, incomes);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getIncomeById(@PathVariable int id){

        Income income = service.getIncomeById(id);

        APIResponse response = new APIResponse("", true, income);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse> getByUserId(@AuthenticationPrincipal UserDetails userDetails){

        List<UserIncome> userIncomes = service.getIncomeByUser(userDetails.getUsername());

        APIResponse response = new APIResponse("", true, userIncomes);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateById(@PathVariable int id, @RequestBody Income newData){

        Income updatedIncome = service.updateIncomeById(id, newData);

        APIResponse response = new APIResponse("", true, updatedIncome);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteById(@PathVariable int id){

        service.deleteIncome(id);

        APIResponse response = new APIResponse("", true, null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
