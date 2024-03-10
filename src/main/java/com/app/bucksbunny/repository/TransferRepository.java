package com.app.bucksbunny.repository;

import com.app.bucksbunny.entity.Transfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfers, Integer> {
}
