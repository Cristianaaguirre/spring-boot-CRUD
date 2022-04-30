package com.folcademy.demo.services;

import com.folcademy.demo.models.Student;

import com.folcademy.demo.repositories.StudentRepository;
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

   @Transactional
   public @NotNull Student getStudent(String id) throws Exception {
      Optional<Student> aux = studentRepository.findById(id);
      if(aux.isEmpty()) throw new Exception("OBJECT DON'T FOUND");
      else return aux.get();
   }

   @Transactional
   public void postStudent(Student aux) throws Exception {
      if(aux == null) throw new Exception("OBJECT NULL");
      studentRepository.save(aux);
   }

   @Transactional
   public void deleteStudent(@NotNull String id) throws Exception {
      if(id.trim().isEmpty()) throw new Exception("INVALID STRING");
      studentRepository.deleteById(id);
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

   @Transactional
   public void putStudent(@NotNull Student auxStudent, String auxId) throws Exception {
      Student student = getStudent(auxId);
      student.setName(auxStudent.getName());
      student.setLastName(auxStudent.getLastName());
      student.setEmail(auxStudent.getEmail());
      postStudent(student);
   }
}
