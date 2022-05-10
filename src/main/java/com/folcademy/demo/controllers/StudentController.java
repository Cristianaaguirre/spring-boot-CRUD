package com.folcademy.demo.controllers;

import com.folcademy.demo.models.DTOs.StudentDTO;
import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.models.entities.Student;
import com.folcademy.demo.services.StudentService;
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

   //=================Gets=================//
   @GetMapping(path = "/get-all-student")
   public ResponseEntity<List<StudentDTO>> getAllStudents() {
      return ResponseEntity.ok(studentService.listToDTO(studentService.getStudents()));
   }
   @GetMapping(path = "/get-student/{id}")
   public ResponseEntity<StudentDTO> getStudent(@PathVariable ("id") String auxId) throws Exception {
      return ResponseEntity.ok(studentService.toDTO(studentService.getStudent(auxId)));
   }
   @GetMapping("/get-by-email-student/{email}")
   public ResponseEntity<StudentDTO> getByEmail(@PathVariable("email") String auxEmail){
      return ResponseEntity.ok(studentService.toDTO(studentService.getByEmail(auxEmail)));
   }

   //=================Post=================//
   @PostMapping(path = "/post-student")
   public ResponseEntity<StudentDTO> postStudent(@RequestBody Student aux){
      return ResponseEntity.status(HttpStatus.CREATED).body(studentService.toDTO(studentService.postStudent(aux)));
   }
   //=================Puts y Patches=================//
   @PutMapping(path = "/put-student/{id}")
   public ResponseEntity<StudentDTO> putStudent(@RequestBody Student student, @PathVariable("id") String id) throws MyException {
      return ResponseEntity.ok(studentService.toDTO(studentService.putStudent(student, id)));
   }
   //=================Delete=================//
   @DeleteMapping(path = "/delete-student/{id}")
   public ResponseEntity<Void> deleteStudent(@PathVariable("id") String auxId) throws Exception {
      studentService.deleteStudent(auxId);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
