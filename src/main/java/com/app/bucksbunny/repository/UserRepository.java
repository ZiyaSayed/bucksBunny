package com.app.bucksbunny.repository;

import com.app.bucksbunny.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

   User findByUserEmail(String userEmail);
   int deleteByUserEmail(String userEmail);

}