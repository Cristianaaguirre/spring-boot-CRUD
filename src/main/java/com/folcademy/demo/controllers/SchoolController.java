package com.folcademy.demo.controllers;

import com.folcademy.demo.models.DTOs.ProfessorDTO;
import com.folcademy.demo.models.DTOs.SchoolDTO;
import com.folcademy.demo.models.DTOs.StudentDTO;
import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.models.entities.School;
import com.folcademy.demo.services.ProfessorService;
import com.folcademy.demo.services.SchoolService;
import com.folcademy.demo.services.StudentService;
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
   @Autowired
   private StudentService studentService;
   @Autowired
   private ProfessorService professorService;

   //=================Gets=================//
   @GetMapping(path = "/get-all")
   public ResponseEntity<List<SchoolDTO>> getAllSchool() {
      return ResponseEntity.ok(schoolService.listToDTO(
         schoolService.getAllSchool()
      ));
   }
   @GetMapping(path = "/get-school/{id}")
   public ResponseEntity<SchoolDTO> getSchool(@PathVariable("id") String auxId){
      return ResponseEntity.ok(schoolService.toDTO(
         schoolService.getSchool(auxId)
      ));
   }
   @GetMapping(path = "/get-students/{id}")
   public ResponseEntity<List<StudentDTO>> getStudents(@PathVariable("id") String id) {
      School aux = schoolService.getSchool(id);
      return ResponseEntity.ok(studentService.listToDTO(aux.getStudentList()));
   }
   @GetMapping(path = "/get-professor/{id}")
   public ResponseEntity<List<ProfessorDTO>> getProfessors(@PathVariable("id") String id) {
      School aux = schoolService.getSchool(id);
      return ResponseEntity.ok(professorService.listToDTO(aux.getProfessorList()));
   }

   //=================Post=================//
   @PostMapping(path = "/post-school")
   public ResponseEntity<SchoolDTO> postSchool(@RequestBody School auxSchool){
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(schoolService.toDTO(
            schoolService.postSchool(auxSchool)
         ));
   }
   //=================Put y patch=================//
   @PutMapping(path = "/put-school/{id}")
   public ResponseEntity<SchoolDTO> putSchool(@RequestBody School auxSchool, @PathVariable("id") String id) {
      return ResponseEntity.ok(schoolService.toDTO(
         schoolService.modifySchool(auxSchool,id)
      ));
   }

   @PatchMapping(path = "/add-student/school-{id-sch}/student-{id-st}")
   public ResponseEntity<SchoolDTO> addStudent(@PathVariable("id-st") String idst, @PathVariable("id-sch") String idsb) throws MyException {
      return ResponseEntity.ok(schoolService.toDTO(
         schoolService.addStudent(idst, idsb)
      ));
   }

   @PatchMapping(path = "/remove-student/school-{id-sc}/student-{id-st}")
   public ResponseEntity<Void> removeStudent(@PathVariable("id-sc") String idsc, @PathVariable("id-st") String idst) throws MyException {
      schoolService.deleteStudent(idsc,idst);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @PatchMapping(path = "/add-professor/school-{id-sch}/professor-{id-pr}")
   public ResponseEntity<SchoolDTO> addProfessor(@PathVariable("id-pr") String idpr, @PathVariable("id-sch") String idsch) throws MyException {
      return ResponseEntity.ok(schoolService.toDTO(
         schoolService.addProfessor(idpr, idsch)
      ));
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
