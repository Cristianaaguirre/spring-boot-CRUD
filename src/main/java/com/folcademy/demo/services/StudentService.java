package com.folcademy.demo.services;

import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.DTOs.StudentDTO;
import com.folcademy.demo.models.entities.Student;
import com.folcademy.demo.models.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor @Transactional
public class StudentService {

   private StudentRepository studentRepository;

   //=================Gets=================//

   public @NotNull Student getStudent(String id){
      return studentRepository
         .findById(id)
         .orElseThrow(() -> new ResourceNotFoundException(id, "student"));
   }

   public Student getByName(String name){
      return studentRepository
         .getByName(name)
         .orElseThrow(() -> new ResourceNotFoundException("DON'T FOUND"));

   }

   public List<Student> getStudents() {
      return studentRepository.findAll();
   }
   //=================Post=================//

   public Student postStudent(@NotNull Student aux){
      return studentRepository.save(aux);
   }
   //=================Puts y patches=================//

   public Student putStudent(@NotNull Student auxStudent, String auxId){
      Student student = studentRepository.getById(auxId);
      student.setName(auxStudent.getName());
      student.setLastName(auxStudent.getLastName());
      student.setEmail(auxStudent.getEmail());
      return postStudent(student);
   }
   //=================Deletes=================//

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
