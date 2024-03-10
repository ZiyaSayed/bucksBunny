package com.app.bucksbunny.controller;

import com.app.bucksbunny.entity.Transfers;
import com.app.bucksbunny.entity.UserTransfers;
import com.app.bucksbunny.response.APIResponse;
import com.app.bucksbunny.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService service;


    @PostMapping("/new")
    public ResponseEntity<APIResponse> addTransfers(@RequestBody Transfers transfer, @AuthenticationPrincipal UserDetails userDetails){

        Transfers newTransfer = service.addTransfer(transfer, userDetails.getUsername());

        APIResponse response = new APIResponse("", true, newTransfer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse> getList(){

        List<Transfers> transferList = service.getAllTransfers();

        APIResponse response = new APIResponse("", true, transferList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable int id){

        Transfers transfer = service.getTransferById(id);

        APIResponse response = new APIResponse("", true, transfer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse> getByUserId(@AuthenticationPrincipal UserDetails userDetails){

        List<UserTransfers> userTransfers = service.getTransfersByUser(userDetails.getUsername());

        APIResponse response = new APIResponse("", true, userTransfers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateById(@PathVariable int id, @RequestBody Transfers newData){

        Transfers transfer = service.updateTransfers(id, newData);

        APIResponse response = new APIResponse("", true, transfer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteById(@PathVariable int id){

         service.deleteTransfer(id);

        APIResponse response = new APIResponse("", true, null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
