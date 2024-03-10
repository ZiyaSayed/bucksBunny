package com.app.bucksbunny.service;

import com.app.bucksbunny.entity.Transfers;
import com.app.bucksbunny.entity.UserTransfers;
import com.app.bucksbunny.exceptions.ResourceNotFoundException;
import com.app.bucksbunny.repository.TransferRepository;
import com.app.bucksbunny.repository.UserTransfersRepository;
import com.app.bucksbunny.serviceInterface.ITransfers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService implements ITransfers {

    @Autowired
    private TransferRepository repo;

    @Autowired
    private UserTransfersRepository mappingRepo;

    @Override
    public Transfers addTransfer(Transfers transfer, String userEmail) {

        // checking if transfer have date & time
        if(transfer.getDate() == null){
            transfer.setDate(LocalDate.now());
        }
        if(transfer.getTime() == null){
            transfer.setTime(LocalTime.now());
        }

        // creating new transfers
        Transfers newTransfer = repo.save(transfer);

        // mapping it to the user transfers
        UserTransfers newUserTransfer = new UserTransfers();
        newUserTransfer.setUserId(userEmail);
        newUserTransfer.setTransfer(newTransfer);

        mappingRepo.save(newUserTransfer);

        return newTransfer;
    }

    @Override
    public Transfers getTransferById(int id) {

        Optional<Transfers> entry = repo.findById(id);

        if(entry.isPresent()){

            Transfers transfer = entry.get();
            return transfer;
        }

        throw new ResourceNotFoundException();
    }

    @Override
    public List<Transfers> getAllTransfers() {

        List<Transfers> transfers = repo.findAll();

        return transfers;
    }

    @Override
    public Transfers updateTransfers(int id, Transfers newData) {

        // get the transfer by id
        Optional<Transfers> entry = repo.findById(id);

        if(entry.isPresent()){

            Transfers prevTransfer = entry.get();

            System.out.println(newData.getAmount());

            if(newData.getToAccountType() != null){
                prevTransfer.setToAccountType(newData.getToAccountType());
            }
            if(newData.getFromAccountType() != null){
                prevTransfer.setFromAccountType(newData.getFromAccountType());
            }
            if(newData.getNotes() != null){
                prevTransfer.setNotes(newData.getNotes());
            }
            if(newData.getAmount() != 0){
                prevTransfer.setAmount(newData.getAmount());
            }
            if(newData.getDate() != null){
                prevTransfer.setDate(newData.getDate());
            }
            if(newData.getTime() != null){
                prevTransfer.setTime(newData.getTime());
            }


            return repo.save(prevTransfer);
        }

        throw new ResourceNotFoundException();

    }

    @Override
    public void deleteTransfer(int id) {

        // getting the entry by id
        Optional<Transfers> entry = repo.findById(id);

        if(entry.isPresent()){

            repo.deleteById(id);
            return;
        }

        throw  new ResourceNotFoundException();

    }
}
