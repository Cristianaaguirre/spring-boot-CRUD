package com.folcademy.demo.controllers;

import com.folcademy.demo.models.Student;
import com.folcademy.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

   @Autowired
   private StudentService studentService;


   //=================Gets=================//

   @GetMapping(path = "/get-all-student")
   public List<Student> getAllStudents() {
      return studentService.getStudents();
   }

   @GetMapping(path = "/get-student/{id}")
   public Student getStudent(@PathVariable ("id") String auxId) throws Exception {
      return studentService.getStudent(auxId);
   }
   @GetMapping("/get-by-email-student/{email}")
   public Student getByEmail(@PathVariable("email") String auxEmail) throws Exception {
      return studentService.getByEmail(auxEmail);
   }

   //=================Post=================//


   @PostMapping(path = "/post-student")
   public void postStudent(@RequestBody Student aux) throws Exception {
      studentService.postStudent(aux);
   }


   //=================Puts y Patches=================//

   @PutMapping(path = "/put-student/{id}")
   public void putStudent(@RequestBody Student student, @PathVariable("id") String id) throws Exception {
      studentService.putStudent(student, id);
   }


   //=================Delete=================//

   //By personal Query
   @DeleteMapping(path = "/delete-student/{id}")
   public void deleteStudent(@PathVariable("id") String auxId) throws Exception {
      studentService.deleteStudent(auxId);
   }
}
