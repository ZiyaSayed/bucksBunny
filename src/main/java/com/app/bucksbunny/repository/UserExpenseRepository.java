package com.app.bucksbunny.repository;

import com.app.bucksbunny.entity.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Integer> {

    List<UserExpense> findAllByUserId(String userEmail);

}
