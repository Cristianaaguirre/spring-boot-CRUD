package com.folcademy.demo.services;

import com.folcademy.demo.DTOs.StudentDTO;
import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.Student;
import com.folcademy.demo.repositories.StudentRepository;
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
   public @NotNull StudentDTO getStudent(String id){
      Student aux = studentRepository
         .findById(id)
         .orElseThrow(() -> new ResourceNotFoundException(id, "student"));
      return toDTO(aux);
   }
   @Transactional
   public StudentDTO getByEmail(String email){
      Student aux = studentRepository
         .getByEmail(email)
         .orElseThrow(() -> new ResourceNotFoundException("DON'T FOUND"));
      return toDTO(aux);
   }
   @Transactional
   public List<StudentDTO> getStudents() {
      return studentRepository
         .findAll()
         .stream()
         .map(this::toDTO)
         .collect(Collectors.toList());
   }
   //=================Post=================//
   @Transactional
   public StudentDTO postStudent(Student aux) throws MyException {
      if(aux == null) throw new MyException("OBJECT NULL");
      return toDTO(studentRepository.save(aux));
   }
   //=================Puts y patches=================//
   @Transactional
   public StudentDTO putStudent(@NotNull Student auxStudent, String auxId) throws MyException {
      Student student = studentRepository.getById(auxId);
      student.setName(auxStudent.getName());
      student.setLastName(auxStudent.getLastName());
      student.setEmail(auxStudent.getEmail());
      return postStudent(student);
   }
   //=================Deletes=================//
   @Transactional
   public void deleteStudent(@NotNull String id) throws MyException {
      if(id.trim().isEmpty()) throw new MyException("INVALID STRING");
      studentRepository.deleteById(id);
   }
   private StudentDTO toDTO(@NotNull Student aux) {
      return StudentDTO.builder()
         .id(aux.getId())
         .name(aux.getName())
         .lastName(aux.getLastName())
         .email(aux.getEmail())
         .build();
   }
}
