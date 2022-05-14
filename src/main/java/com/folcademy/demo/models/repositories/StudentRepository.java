package com.folcademy.demo.models.repositories;

import com.folcademy.demo.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

   @Query("select e from Student e where e.name = ?1")
   Optional<Student> getByName(String name);

}