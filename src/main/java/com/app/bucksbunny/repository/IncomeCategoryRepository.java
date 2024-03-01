package com.app.bucksbunny.repository;


import com.app.bucksbunny.entity.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Integer> {

}
