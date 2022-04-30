package com.folcademy.demo.services;

import com.folcademy.demo.models.Student;


import com.folcademy.demo.models.Subject;
import com.folcademy.demo.repositories.StudentRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

   @Autowired
   private StudentRepository studentRepository;

   //=============Gets============//
   @Transactional
   public @NotNull Student getStudent(String id) {
      return studentRepository
         .findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Element don't found for this id: " + id));
   }

   @Transactional
   public List<Student> getStudents() {
      return studentRepository.findAll();
   }

   @Transactional
   public Student getByEmail(String email) throws Exception {
      Optional<Student> auxEmail = studentRepository.getByEmail(email);
      if(auxEmail.isPresent())return auxEmail.get();
      else throw new Exception("OBJECT DON'T FOUND");
   }


   //=============Post===============//
   @Transactional
   public Student postStudent(Student aux) throws Exception {
      if(aux == null) throw new Exception("OBJECT NULL");
      return studentRepository.save(aux);
   }

   //=============Put y patch================//
   @Transactional
   public void putStudent(@NotNull Student auxStudent, String auxId) throws Exception {
      Student student = getStudent(auxId);
      student.setName(auxStudent.getName());
      student.setLastName(auxStudent.getLastName());
      student.setEmail(auxStudent.getEmail());
      postStudent(student);
   }


   //===========Delete=============//
   @Transactional
   public void deleteStudent(@NotNull String id) {
      Student auxStudent = getStudent(id);
      studentRepository.delete(auxStudent);
   }

}
