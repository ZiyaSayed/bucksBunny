package com.app.bucksbunny.controller;

import com.app.bucksbunny.entity.Budget;
import com.app.bucksbunny.entity.ExpenseCategory;
import com.app.bucksbunny.entity.ExpenseCategoryMapping;
import com.app.bucksbunny.request.UpdateBudgetBody;
import com.app.bucksbunny.request.UpdateCategoryBody;
import com.app.bucksbunny.response.APIResponse;
import com.app.bucksbunny.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense/category")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService service;

    @PostMapping("/new")
    public ResponseEntity<APIResponse> addNewExpenseCategory(@RequestBody ExpenseCategory category, @AuthenticationPrincipal UserDetails userDetails){

        ExpenseCategory newCategory = service.addExpenseCategory(category, userDetails.getUsername());

        APIResponse response = new APIResponse("", true, newCategory);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<APIResponse> getList(){

        List<ExpenseCategory> categories = service.getAllExpenseCategory();

        APIResponse response = new APIResponse("", true, categories);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable int id){


        ExpenseCategory category = service.getExpenseCategoryById(id);

        APIResponse response = new APIResponse("", true, category);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse> getByUserId(@AuthenticationPrincipal UserDetails userDetails){

        List<ExpenseCategoryMapping> userCategories = service.getExpenseCategoryByUser(userDetails.getUsername());

        APIResponse response = new APIResponse("", true, userCategories);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateById(@PathVariable int id, @RequestBody UpdateCategoryBody newData){

        ExpenseCategory newCategory = service.updateExpenseCategoryById(id,newData);

        APIResponse response = new APIResponse("", true, newCategory);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteById(@PathVariable int id){

        service.deleteExpenseCategory(id);

        APIResponse response = new APIResponse("", true, null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // =========== Budget services ===========

    @PostMapping("/budget/new")
    public ResponseEntity<APIResponse> addNewBudget(@RequestBody Budget budget){

        Budget newBudget = service.addBudget(budget);

        APIResponse response = new APIResponse("", true, newBudget);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/budget/{id}")
    public ResponseEntity<APIResponse> getBudgetById(@PathVariable int id){

        Budget budget = service.getBudgetById(id);

        APIResponse response = new APIResponse("", true, budget);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PatchMapping("/budget/{id}")
    public ResponseEntity<APIResponse> updateBudgetById(@PathVariable int id, @RequestBody UpdateBudgetBody limit){

        Budget newBudget = service.updateBudgetById(id,limit);

        APIResponse response = new APIResponse("", true, newBudget);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
