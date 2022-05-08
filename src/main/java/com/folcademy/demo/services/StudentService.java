package com.folcademy.demo.services;

import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.Student;
import com.folcademy.demo.repositories.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
   public void postStudent(Student aux) throws MyException {
      if(aux == null) throw new MyException("OBJECT NULL");
      studentRepository.save(aux);
   }


   //=================Puts y patches=================//

   @Transactional
   public void putStudent(@NotNull Student auxStudent, String auxId) throws MyException {
      Student student = getStudent(auxId);
      student.setName(auxStudent.getName());
      student.setLastName(auxStudent.getLastName());
      student.setEmail(auxStudent.getEmail());
      postStudent(student);
   }

   //=================Deletes=================//

   @Transactional
   public void deleteStudent(@NotNull String id) throws Exception {
      if(id.trim().isEmpty()) throw new Exception("INVALID STRING");
      studentRepository.deleteById(id);
   }
}
