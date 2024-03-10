package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.UserTransfers;

import java.util.List;

public interface IUserTransfer {

    // ========= GET =========
    public List<UserTransfers> getTransfersByUser(String userEmail);
}
