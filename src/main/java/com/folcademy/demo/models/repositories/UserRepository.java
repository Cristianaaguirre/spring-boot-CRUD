package com.folcademy.demo.models.repositories;

import com.folcademy.demo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   @Query("select u from User u where u.name = ?1")
   User findUserByName(String name);
}