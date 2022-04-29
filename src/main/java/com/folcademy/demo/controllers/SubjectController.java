package com.folcademy.demo.controllers;

import com.folcademy.demo.models.Subject;
import com.folcademy.demo.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
