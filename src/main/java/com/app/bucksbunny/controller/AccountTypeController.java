package com.app.bucksbunny.controller;

import com.app.bucksbunny.entity.AccountType;
import com.app.bucksbunny.entity.AccountTypeMapping;
import com.app.bucksbunny.response.APIResponse;
import com.app.bucksbunny.service.AccountTypeMappingService;
import com.app.bucksbunny.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountTypeController {

    @Autowired
    private AccountTypeService service;

    @Autowired
    private AccountTypeMappingService mappingService;

    @PostMapping("/new")
    public ResponseEntity<APIResponse> addNewAccountType(@RequestBody AccountType accountType, @AuthenticationPrincipal UserDetails userDetails){

        AccountType newAccount = service.addAccountType(accountType, userDetails.getUsername());

        APIResponse response = new APIResponse("", true, newAccount);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse> getList(){

        List<AccountType> accounts = service.getAllAccountType();

        APIResponse response = new APIResponse("", true, accounts);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable int id){


        AccountType account = service.getAccountTypeById(id);

        APIResponse response = new APIResponse("", true, account);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse> getByUserId(@AuthenticationPrincipal UserDetails userDetails){


        List<AccountTypeMapping> userAccounts = mappingService.getAccountTypeByUser(userDetails.getUsername());

        APIResponse response = new APIResponse("", true, userAccounts);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateById(@PathVariable int id, @RequestBody AccountType newData){

        AccountType newAccountType = service.updateAccountType(id,newData);

        APIResponse response = new APIResponse("", true, newAccountType);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteById(@PathVariable int id){

        service.deleteAccountType(id);

        APIResponse response = new APIResponse("", true, null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
