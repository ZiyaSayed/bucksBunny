package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.IncomeCategoryMapping;

import java.util.List;

public interface IIncomeCategoryMapping {

    // ======== GET =========

    // get category by userName
    public List<IncomeCategoryMapping> getIncomeCategoryByUser(String userEmail);
}

