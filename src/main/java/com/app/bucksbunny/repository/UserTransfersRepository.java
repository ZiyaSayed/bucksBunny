package com.app.bucksbunny.repository;

import com.app.bucksbunny.entity.UserTransfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTransfersRepository extends JpaRepository<UserTransfers, Integer> {

    List<UserTransfers> findAllByUserId(String userEmail);
}
