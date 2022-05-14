package com.folcademy.demo.controllers;

import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.models.DTOs.ProfessorDTO;
import com.folcademy.demo.models.DTOs.SchoolDTO;
import com.folcademy.demo.models.DTOs.StudentDTO;
import com.folcademy.demo.models.entities.School;
import com.folcademy.demo.services.ProfessorService;
import com.folcademy.demo.services.SchoolService;
import com.folcademy.demo.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
@AllArgsConstructor
public class SchoolController {

   private SchoolService schoolService;
   private StudentService studentService;
   private ProfessorService professorService;

   //=================Gets=================//

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

   //=================Put=================//

   @PutMapping(path = "/put-school/{id}")
   public ResponseEntity<SchoolDTO> putSchool(@RequestBody School auxSchool, @PathVariable("id") String id) {
      return ResponseEntity.ok(schoolService.toDTO(
         schoolService.modifySchool(auxSchool, id)
      ));
   }

   //=================Patches=================//

   @PatchMapping(path = "/add-student/school-{id-sch}/student-{id-st}")
   public ResponseEntity<SchoolDTO> addStudent(@PathVariable("id-st") String idst, @PathVariable("id-sch") String idsb) throws MyException {
      return ResponseEntity.ok(schoolService.toDTO(
         schoolService.addStudent(idst, idsb)
      ));
   }

   @PatchMapping(path = "/remove-student/school-{id-sc}/student-{id-st}")
   public ResponseEntity<Void> removeStudent(@PathVariable("id-sc") String idsc, @PathVariable("id-st") String idst) throws MyException {
      schoolService.deleteStudent(idsc, idst);
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
}
