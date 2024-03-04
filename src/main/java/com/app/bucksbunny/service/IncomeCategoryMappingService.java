package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.IncomeCategoryMapping;
import com.app.bucksbunny.exceptions.UserNotFoundException;
import com.app.bucksbunny.repository.IncomeCategoryMappingRepository;
import com.app.bucksbunny.serviceInterface.IIncomeCategoryMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeCategoryMappingService implements IIncomeCategoryMapping {

    @Autowired
    private IncomeCategoryMappingRepository repo;


    @Override
    public List<IncomeCategoryMapping> getIncomeCategoryByUser(String userEmail) throws UserNotFoundException {

        List<IncomeCategoryMapping> userCategories = repo.findAllByUserId(userEmail);

        return userCategories;
    }
}
