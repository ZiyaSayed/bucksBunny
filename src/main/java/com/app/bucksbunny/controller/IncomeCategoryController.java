package com.app.bucksbunny.controller;

import com.app.bucksbunny.entity.IncomeCategory;
import com.app.bucksbunny.entity.IncomeCategoryMapping;
import com.app.bucksbunny.request.UpdateCategoryBody;
import com.app.bucksbunny.response.APIResponse;
import com.app.bucksbunny.service.IncomeCategoryMappingService;
import com.app.bucksbunny.service.IncomeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class IncomeCategoryController {

    @Autowired
    private IncomeCategoryService service;

    @Autowired
    private IncomeCategoryMappingService mappingService;

    @PostMapping("/new")
    public ResponseEntity<APIResponse> addNewIncomeCategory(@RequestBody IncomeCategory category, @AuthenticationPrincipal UserDetails userDetails){

        IncomeCategory newCategory = service.addIncomeCategory(category, userDetails.getUsername());

        APIResponse response = new APIResponse("", true, newCategory);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<APIResponse> getList(){

        List<IncomeCategory> categories = service.getAllIncomeCategory();

        APIResponse response = new APIResponse("", true, categories);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable int id){


            IncomeCategory category = service.getIncomeCategoryById(id);

            APIResponse response = new APIResponse("", true, category);

            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse> getByUserId(@AuthenticationPrincipal UserDetails userDetails){


        List<IncomeCategoryMapping> userCategories = mappingService.getIncomeCategoryByUser(userDetails.getUsername());

        APIResponse response = new APIResponse("", true, userCategories);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateById(@PathVariable int id, @RequestBody UpdateCategoryBody newData){

        IncomeCategory newCategory = service.updateIncomeCategoryById(id,newData);

        APIResponse response = new APIResponse("", true, newCategory);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteById(@PathVariable int id){

        service.deleteIncomeCategory(id);

        APIResponse response = new APIResponse("", true, null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
