package com.app.bucksbunny.repository;

import com.app.bucksbunny.entity.ExpenseCategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseCategoryMappingRepository extends JpaRepository<ExpenseCategoryMapping, Integer> {

    List<ExpenseCategoryMapping> findAllByUserId(String userEmail);
}
