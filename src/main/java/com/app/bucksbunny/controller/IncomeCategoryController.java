package com.app.bucksbunny.controller;

import com.app.bucksbunny.entity.IncomeCategory;
import com.app.bucksbunny.service.IncomeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class IncomeCategoryController {

    @Autowired
    private IncomeCategoryService service;

    @PostMapping("/new")
    public IncomeCategory addNewIncomeCategory(@RequestBody IncomeCategory category){

        return service.addIncomeCategory(category);
    }
}
