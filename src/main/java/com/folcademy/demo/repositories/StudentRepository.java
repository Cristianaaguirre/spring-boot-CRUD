package com.folcademy.demo.repositories;

import com.folcademy.demo.models.Student;
import com.folcademy.demo.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

   @Query("select e from Student e where e.email = ?1")
   Optional<Student> getByEmail(String email);

}