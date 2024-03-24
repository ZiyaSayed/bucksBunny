package com.app.bucksbunny.controller;

import com.app.bucksbunny.entity.Expense;
import com.app.bucksbunny.entity.UserExpense;
import com.app.bucksbunny.response.APIResponse;
import com.app.bucksbunny.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping("/new")
    public ResponseEntity<APIResponse> addExpense(@RequestBody Expense expense, @AuthenticationPrincipal UserDetails userDetails){

        Expense newExpense = service.addExpense(expense, userDetails.getUsername());

        APIResponse response = new APIResponse("", true, newExpense);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse> getList(){

        List<Expense> expenses = service.getAllExpense();

        APIResponse response = new APIResponse("", true, expenses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getExpenseById(@PathVariable int id){

        Expense expense = service.getExpenseById(id);

        APIResponse response = new APIResponse("", true, expense);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse> getByUserId(@AuthenticationPrincipal UserDetails userDetails){

        List<UserExpense> userExpenses = service.getExpenseByUser(userDetails.getUsername());

        APIResponse response = new APIResponse("", true, userExpenses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateById(@PathVariable int id, @RequestBody Expense newData){

        Expense updatedExpense = service.updateExpenseById(id, newData);

        APIResponse response = new APIResponse("", true, updatedExpense);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteById(@PathVariable int id){

        service.deleteExpense(id);

        APIResponse response = new APIResponse("", true, null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
