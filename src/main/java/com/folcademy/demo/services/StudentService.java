package com.folcademy.demo.services;

import com.folcademy.demo.models.DTOs.StudentDTO;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.entities.Student;
import com.folcademy.demo.models.repositories.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

   @Autowired
   private StudentRepository studentRepository;

   //=================Gets=================//
   @Transactional
   public @NotNull Student getStudent(String id){
      return studentRepository
         .findById(id)
         .orElseThrow(() -> new ResourceNotFoundException(id, "student"));
   }
   @Transactional
   public Student getByEmail(String email){
      return studentRepository
         .getByEmail(email)
         .orElseThrow(() -> new ResourceNotFoundException("DON'T FOUND"));

   }
   @Transactional
   public List<Student> getStudents() {
      return studentRepository.findAll();
   }
   //=================Post=================//
   @Transactional
   public Student postStudent(@NotNull Student aux){
      return studentRepository.save(aux);
   }
   //=================Puts y patches=================//
   @Transactional
   public Student putStudent(@NotNull Student auxStudent, String auxId){
      Student student = studentRepository.getById(auxId);
      student.setName(auxStudent.getName());
      student.setLastName(auxStudent.getLastName());
      student.setEmail(auxStudent.getEmail());
      return postStudent(student);
   }
   //=================Deletes=================//
   @Transactional
   public void deleteStudent(@NotNull String id){
      studentRepository.deleteById(id);
   }

   //=================DTOs method=================//
   public StudentDTO toDTO(@NotNull Student aux) {
      return StudentDTO.builder()
         .id(aux.getId())
         .name(aux.getName())
         .lastName(aux.getLastName())
         .email(aux.getEmail())
         .build();
   }
   public List<StudentDTO> listToDTO(@NotNull List<Student> list) {
      return list
         .stream()
         .map(this::toDTO)
         .collect(Collectors.toList());
   }
}
