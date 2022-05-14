package com.folcademy.demo.controllers;

import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.models.DTOs.StudentDTO;
import com.folcademy.demo.models.entities.Student;
import com.folcademy.demo.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
@AllArgsConstructor
public class StudentController {

   private StudentService studentService;

   //=================Gets=================//

   @GetMapping(path = "/get-all-student")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public ResponseEntity<List<StudentDTO>> getAllStudents() {
      return ResponseEntity.ok(studentService.listToDTO(studentService.getStudents()));
   }

   @GetMapping(path = "/get-student/{id}")
   @PreAuthorize("hasAuthority('student:read')")
   public ResponseEntity<StudentDTO> getStudent(@PathVariable ("id") String auxId) {
      return ResponseEntity.ok(studentService.toDTO(studentService.getStudent(auxId)));
   }

   @GetMapping("/get-by-email-student/{name}")
   @PreAuthorize("hasAuthority('student:read')")
   public ResponseEntity<StudentDTO> getByName(@PathVariable("name") String name){
      return ResponseEntity.ok(studentService.toDTO(studentService.getByName(name)));
   }

   //=================Post=================//

   @PostMapping(path = "/post-student")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
   public ResponseEntity<StudentDTO> postStudent(@RequestBody Student aux){
      return ResponseEntity.status(HttpStatus.CREATED).body(studentService.toDTO(studentService.postStudent(aux)));
   }

   //=================Puts y Patches=================//

   @PutMapping(path = "/put-student/{id}")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
   public ResponseEntity<StudentDTO> putStudent(@RequestBody Student student, @PathVariable("id") String id) throws MyException {
      return ResponseEntity.ok(studentService.toDTO(studentService.putStudent(student, id)));
   }

   //=================Delete=================//

   @DeleteMapping(path = "/delete-student/{id}")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
   public ResponseEntity<Void> deleteStudent(@PathVariable("id") String auxId) throws Exception {
      studentService.deleteStudent(auxId);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
