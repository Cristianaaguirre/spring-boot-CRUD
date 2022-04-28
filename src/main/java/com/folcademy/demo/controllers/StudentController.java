package com.folcademy.demo.controllers;

import com.folcademy.demo.models.Student;
import com.folcademy.demo.services.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

   @Autowired
   private StudentService studentService;

   @GetMapping(path = "/get-all-student")
   public List<Student> getAllStudents() {
      return studentService.getStudents();
   }

   @GetMapping(path = "/get-student/{id}")
   public Student getStudent(@PathVariable ("id") String auxId) throws Exception {
      return studentService.getStudent(auxId);
   }

   @PostMapping(path = "/post-student")
   public void postStudent(@RequestBody Student aux) throws Exception {
      studentService.postStudent(aux);
   }

   @DeleteMapping(path = "/delete-student/{id}")
   public void deleteStudent(@PathVariable("id") String auxId) throws Exception {
      studentService.deleteStudent(auxId);
   }

   @PutMapping(path = "/put-student/{id}")
   public void putStudent(@RequestBody @NotNull Student student, @PathVariable("id") String id) throws Exception {
      Student auxStudent = studentService.getStudent(id);
      auxStudent.setName(student.getName());
      auxStudent.setLastName(student.getLastName());
      auxStudent.setEmail(student.getEmail());
      studentService.postStudent(auxStudent);
   }

   //By personal Query
   @GetMapping("/get-by-email-student/{email}")
   public Student getByEmail(@PathVariable("email") String auxEmail) throws Exception {
      return studentService.getByEmail(auxEmail);
   }
}
