package com.folcademy.demo.controllers;

import com.folcademy.demo.models.Student;
import com.folcademy.demo.services.StudentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

   @Autowired
   private StudentService studentService;

   ///=============Gets===========//
   @GetMapping(path = "/get-all-student")
   public List<Student> getAllStudents() {
      return studentService.getStudents();
   }

   @GetMapping(path = "/get-student/{id}")
   public ResponseEntity<Student> getStudent(@PathVariable ("id") String auxId) throws Exception {
      return ResponseEntity
         .status(HttpStatus.OK)
         .body(studentService.getStudent(auxId));
   }

   @GetMapping("/get-by-email-student/{email}")
   public Student getByEmail(@PathVariable("email") String auxEmail) throws Exception {
      return studentService.getByEmail(auxEmail);
   }

   //==========Post===========//

   @PostMapping(path = "/post-student")
   public ResponseEntity<Student> postStudent(@RequestBody Student aux) throws Exception {
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(studentService.postStudent(aux));
   }

   //=========Put y Patch=========//
   @PutMapping(path = "/put-student/{id}")
   public void putStudent(@RequestBody Student student, @PathVariable("id") String id) throws Exception {
      studentService.putStudent(student, id);
   }

   //========Delete=========//

   @DeleteMapping(path = "/delete-student/{id}")
   public void deleteStudent(@PathVariable("id") String auxId) throws Exception {
      studentService.deleteStudent(auxId);
   }

}
