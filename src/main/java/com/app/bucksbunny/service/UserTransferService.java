package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.UserTransfers;
import com.app.bucksbunny.exceptions.UserNotFoundException;
import com.app.bucksbunny.repository.UserTransfersRepository;
import com.app.bucksbunny.serviceInterface.IUserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTransferService implements IUserTransfer {

    @Autowired
    private UserTransfersRepository repo;

    @Override
    public List<UserTransfers> getTransfersByUser(String userEmail) throws UserNotFoundException {

        List<UserTransfers> userTransfers = repo.findAllByUserId(userEmail);

        return userTransfers;
    }
}
