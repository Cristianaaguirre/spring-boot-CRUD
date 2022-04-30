package com.folcademy.demo.controllers;

import com.folcademy.demo.models.School;
import com.folcademy.demo.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

   @Autowired
   private SchoolService schoolService;

   //=================Gets=================//
   @GetMapping(path = "/get-all")
   public ResponseEntity<List<School>> getAll() {
      return ResponseEntity
         .status(HttpStatus.OK)
         .body(schoolService.getAllSubject());
   }

   @GetMapping(path = "/get-one-school/{id}")
   public ResponseEntity<School> getSchool(@PathVariable("id") String auxId) {
      return ResponseEntity
         .status(HttpStatus.OK)
         .body(schoolService.getSchool(auxId));
   }

   //=================Post=================//

   @PostMapping(path = "/post-school")
   public ResponseEntity<School> postSchool(@RequestBody School auxSchool){
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(schoolService.postSchool(auxSchool));
   }

   //=================Put y patch=================//

   @PutMapping(path = "/put-school/{id}")
   public ResponseEntity<School> putSchool(@RequestBody School auxSchool, @PathVariable("id") String id) {
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(schoolService.putSchool(auxSchool,id));
   }

   @PatchMapping(path = "/add-student/{id-st}/{id-sb}")
   public ResponseEntity<School> addStudent(@PathVariable("id-st") String idst, @PathVariable("id-sb") String idsb) throws Exception {
      return ResponseEntity
         .status(HttpStatus.OK)
         .body(schoolService.patchStudent(idst, idsb));
   }

   @PatchMapping(path = "/remove-student/school/{id-sc}/student/{id-st}")
   public ResponseEntity<School> removeStudent(@PathVariable("id-sc") String idsc, @PathVariable("id-st") String idst) throws Exception {
      return ResponseEntity
         .status(HttpStatus.NO_CONTENT)
         .body(schoolService.deleteStudent(idsc, idst));
   }

   //=================Delete=================//

   @DeleteMapping(path = "/delete-school/{id}")
   public void deleteSubject(@PathVariable("id") String id) {
      schoolService.deleteSchool(id);
   }


}