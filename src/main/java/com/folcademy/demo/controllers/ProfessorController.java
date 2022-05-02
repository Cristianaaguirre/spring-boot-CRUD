package com.folcademy.demo.controllers;

import com.folcademy.demo.models.Professor;
import com.folcademy.demo.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {

   @Autowired
   private ProfessorService professorService;

   //=================Gets=================//

   @GetMapping(path = "/get-pro/{id}")
   public ResponseEntity<Professor> getProf(@PathVariable("id") String id) throws Exception {
      return ResponseEntity
         .status(HttpStatus.OK)
         .body(professorService.getProfessor(id));
   }

   @GetMapping(path = "/get-all-professor")
   public ResponseEntity<List<Professor>> getAllPro(){
      return ResponseEntity.ok(professorService.getAllProfessors());
   }
   //=================Post=================//

   @PostMapping(path = "/post-professor")
   public ResponseEntity<?> postPro(@RequestBody Professor aux) {
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(professorService.postProfessor(aux));
   }

   //=================Puts y Patches=================//

   @PutMapping(path = "put-professor/{id}")
   public ResponseEntity<Professor> putPro(@RequestBody Professor aux, @PathVariable("id") String id) {
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(professorService.putProfessor(aux,id));
   }

   //=================Deletes=================//

   @DeleteMapping(path = "/delete-professor/{id}")
   public ResponseEntity<HttpStatus> deletePro(@PathVariable("id") String id) {
      professorService.deleteProfessor(id);
      return ResponseEntity.ok(HttpStatus.NO_CONTENT);
   }
}
