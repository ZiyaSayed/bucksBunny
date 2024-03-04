package com.app.bucksbunny.repository;

import com.app.bucksbunny.entity.AccountTypeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeMappingRepository extends JpaRepository<AccountTypeMapping, Integer> {

    List<AccountTypeMapping> findAllByUserId(String userEmail);
}
