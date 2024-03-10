package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.Transfers;

import java.util.List;

public interface ITransfers {

    // ========= POST =========
    public Transfers addTransfer(Transfers transfer, String userEmail);

    // ========= GET =========
    public Transfers getTransferById(int id);

    public List<Transfers> getAllTransfers();

    // ========= UPDATE =========
    public Transfers updateTransfers(int id, Transfers newData);

    // ========= DELETE =========
    public void deleteTransfer(int id);
}
