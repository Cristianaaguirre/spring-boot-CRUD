package com.folcademy.demo.controllers;

import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.models.DTOs.StudentDTO;
import com.folcademy.demo.models.DTOs.SubjectDTO;
import com.folcademy.demo.models.DTOs.SubjectProfessorDTO;
import com.folcademy.demo.models.entities.Professor;
import com.folcademy.demo.models.entities.Subject;
import com.folcademy.demo.services.ProfessorService;
import com.folcademy.demo.services.StudentService;
import com.folcademy.demo.services.SubjectService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/subject")
@AllArgsConstructor
public class SubjectController {

   private SubjectService subjectService;
   private StudentService studentService;
   private ProfessorService professorService;

   //=================Gets=================//
   @GetMapping(path = "/get-subject/{id}")
   public ResponseEntity<SubjectDTO> getSubject(@PathVariable("id") Long id) {
      return ResponseEntity.ok(subjectToDTO(subjectService.getSubject(id)));
   }

   @GetMapping(path = "/get-all")
   public ResponseEntity<List<SubjectDTO>> getAllSubject() throws MyException {
      return ResponseEntity.ok(listSubjectToDTO(subjectService.getAllSubject()));
   }

   @GetMapping(path = "/get-all-student/{id}")
   public ResponseEntity<List<StudentDTO>> getStudentInSubject(@PathVariable("id") Long id) {
      return ResponseEntity.ok(studentService.listToDTO(
         subjectService.getStudentSubject(id))
      );
   }

   //=================Post=================//
   @PostMapping(path = "/post-subject")
   public ResponseEntity<SubjectDTO> postSubject(@RequestBody Subject aux) {
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(subjectToDTO(subjectService.postSubject(aux)));
   }

   //=================Put y patch=================//
   @PutMapping(path = "/put-subject/{id}")
   public ResponseEntity<SubjectDTO> putSubject(@RequestBody Subject aux, @PathVariable("id") Long id) {
      return ResponseEntity.ok(subjectToDTO(subjectService.putSubject(aux,id)));
   }

   @PatchMapping(path = "/add-professor/subject-{id-sb}/professor-{id-pr}")
   public ResponseEntity<SubjectProfessorDTO> addProfessor(@PathVariable("id-sb") Long idSb, @PathVariable("id-pr") String idPr) throws MyException {
      subjectService.addProfessor(idSb, idPr);
      return ResponseEntity.ok(
         getProfessorSubject(subjectService.getSubject(idSb), professorService.getProfessor(idPr))
      );
   }

   @PatchMapping(path = "/set-professor/subject-{id-sb}/professor-{id-pr}")
   public ResponseEntity<Void> removeProfessor(@PathVariable("id-sb") Long idSb, @PathVariable("id-pr") String idPr) throws MyException {
      subjectService.removeProfessor(idSb, idPr);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @PatchMapping(path = "/add-student/subject-{id-sb}/student-{id-st}")
   public ResponseEntity<List<StudentDTO>> addStudent(@PathVariable("id-sb") Long idSb, @PathVariable("id-st") String idSt) throws MyException {
      subjectService.addStudent(idSb, idSt);
      return ResponseEntity.ok(studentService.listToDTO(
         subjectService.getStudentSubject(idSb)
      ));
   }

   @PatchMapping(path = "/remove-student/subject-{id-sb}/student-{id-st}")
   public ResponseEntity<Void> removeStudent(@PathVariable("id-sb") Long idSb, @PathVariable("id-st") String idSt){
      subjectService.removeStudent(idSb,idSt);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   //=================Delete=================//

   @DeleteMapping(path = "/delete-subject/{id}")
   public ResponseEntity<Void> deleteSubject(@PathVariable("id") Long id) {
      subjectService.deleteSubject(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   //=================DTOs method=================//

   public SubjectProfessorDTO getProfessorSubject(@NotNull Subject subject, @NotNull Professor professor) {
      return SubjectProfessorDTO.builder()
         .id(subject.getId())
         .name(subject.getName())
         .professorName(professor.getName())
         .build();
   }

   public SubjectDTO subjectToDTO(@NotNull Subject aux) {
      return SubjectDTO.builder()
         .id(aux.getId())
         .name(aux.getName())
         .build();
   }

   public List<SubjectDTO> listSubjectToDTO(@NotNull List<Subject> list) {
      return list
         .stream()
         .map(this::subjectToDTO)
         .collect(Collectors.toList());
   }
}
