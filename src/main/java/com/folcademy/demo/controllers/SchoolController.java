package com.folcademy.demo.controllers;

import com.folcademy.demo.exceptions.MyException;
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
   public ResponseEntity<List<School>> getAllSchool() {
      return ResponseEntity.ok(schoolService.getAllSubject());
   }
   @GetMapping(path = "/get-one-school/{id}")
   public ResponseEntity<School> getSchool(@PathVariable("id") String auxId){
      return ResponseEntity.ok(schoolService.getSchool(auxId));
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
      return ResponseEntity.ok(schoolService.modifySchool(auxSchool,id));
   }

   @PatchMapping(path = "/add-student/school-{id-sch}/student-{id-st}")
   public ResponseEntity<School> addStudent(@PathVariable("id-st") String idst, @PathVariable("id-sch") String idsb) throws MyException {
      return ResponseEntity.ok(schoolService.addStudent(idst, idsb));
   }

   @PatchMapping(path = "/remove-student/school-{id-sc}/student-{id-st}")
   public ResponseEntity<Void> removeStudent(@PathVariable("id-sc") String idsc, @PathVariable("id-st") String idst) throws MyException {
      schoolService.deleteStudent(idsc,idst);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @PatchMapping(path = "/add-professor/school-{id-sch}/professor-{id-pr}")
   public ResponseEntity<School> addProfessor(@PathVariable("id-pr") String idpr, @PathVariable("id-sch") String idsch) throws MyException {
      return ResponseEntity.ok(schoolService.addProfessor(idpr, idsch));
   }

   @PatchMapping(path = "/remove-professor/school-{id-sch}/professor-{id-pr}")
   public ResponseEntity<Void> removeProfessor(@PathVariable("id-pr") String idpr, @PathVariable("id-sch") String idsch) throws MyException {
      schoolService.deleteProfessor(idpr, idsch);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   //=================Delete=================//

   @DeleteMapping(path = "/delete-school/{id}")
   public ResponseEntity<Void> deleteSubject(@PathVariable("id") String id) {
      schoolService.deleteSchool(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
