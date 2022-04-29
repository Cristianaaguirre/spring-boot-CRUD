package com.folcademy.demo.controllers;

import com.folcademy.demo.models.Subject;
import com.folcademy.demo.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

   @Autowired
   private SubjectService subjectService;

   @GetMapping(path = "/get-one-subject/{id}")
   public ResponseEntity<Subject> getSubject(@PathVariable("id") String auxId) {
      return ResponseEntity
         .status(HttpStatus.OK)
         .body(subjectService.getSubject(auxId));
   }

   @GetMapping(path = "/get-all")
   public ResponseEntity<List<Subject>> getAll() {
      return ResponseEntity
         .status(HttpStatus.OK)
         .body(subjectService.getAllSubject());
   }

   @PostMapping(path = "/post-subject")
   public void postSubject(@RequestBody Subject auxSubject){
      subjectService.postSubject(auxSubject);
   }

   @PutMapping(path = "/put-subject/{id}")
   public void putSubject(@RequestBody Subject auxSubject, @PathVariable("id") String id) {
      subjectService.putSubject(auxSubject,id);
   }

   @DeleteMapping(path = "/delete-subject/{id}")
   public void deleteSubject(@PathVariable("id") String id) {
      subjectService.deleteSubject(id);
   }

}
