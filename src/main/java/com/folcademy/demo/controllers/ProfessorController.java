package com.folcademy.demo.controllers;

import com.folcademy.demo.models.DTOs.ProfessorDTO;
import com.folcademy.demo.models.entities.Professor;
import com.folcademy.demo.services.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professor")
@AllArgsConstructor
public class ProfessorController {

   private ProfessorService professorService;

   //=================Gets=================//

   @GetMapping(path = "/get-pro/{id}")
   public ResponseEntity<ProfessorDTO> getProf(@PathVariable("id") String id){
      return ResponseEntity.ok(professorService.toDTO(professorService.getProfessor(id)));
   }

   @GetMapping(path = "/get-all-professor")
   public ResponseEntity<List<ProfessorDTO>> getAllPro(){
      return ResponseEntity.ok(professorService.listToDTO(professorService.getAllProfessors()));
   }
   //=================Post=================//

   @PostMapping(path = "/post-professor")
   public ResponseEntity<ProfessorDTO> postPro(@RequestBody Professor aux) {
      return ResponseEntity
         .status(HttpStatus.CREATED)
         .body(professorService.toDTO(professorService.postProfessor(aux)));
   }

   //=================Puts y Patches=================//

   @PutMapping(path = "put-professor/{id}")
   public ResponseEntity<ProfessorDTO> putProfessor(@RequestBody Professor aux, @PathVariable("id") String id) {
      return ResponseEntity.ok(professorService.toDTO(professorService.putProfessor(aux,id)));
   }

   //=================Deletes=================//

   @DeleteMapping(path = "/delete-professor/{id}")
   public ResponseEntity<Void> deleteProfessor(@PathVariable("id") String id) {
      professorService.deleteProfessor(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
