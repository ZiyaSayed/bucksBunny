package com.app.bucksbunny.repository;

import com.app.bucksbunny.entity.IncomeCategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeCategoryMappingRepository extends JpaRepository<IncomeCategoryMapping, Integer> {

    List<IncomeCategoryMapping> findAllByUserId(String userEmail);

}
