package com.app.bucksbunny.repository;

import com.app.bucksbunny.entity.UserIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIncomeRepository extends JpaRepository<UserIncome, Integer> {

    // GET
    List<UserIncome> findAllByUserId(String userEmail);
}
