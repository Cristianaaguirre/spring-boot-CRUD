package com.folcademy.demo.services;


import com.folcademy.demo.models.Professor;
import com.folcademy.demo.repositories.ProfessorRepository;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ProfessorService {

   @Autowired
   private ProfessorRepository professorRepository;

   //=================Gets=================//

   public @NotNull Professor getProfessor(String id){
      return professorRepository
         .findById(id)
         .orElseThrow(() -> new ResourceNotFoundException(id, "professor"));
   }

   public List<Professor> getAllProfessors(){
      return professorRepository.findAll();
   }

   //=================Post=================//
   @Transactional
   public Professor postProfessor(@NotNull @RequestBody Professor auxPro) {
      return professorRepository.save(auxPro);
   }

   //=================Puts y Patches=================//

   @Transactional
   public Professor putProfessor(@NotNull Professor aux, String id) {
      Professor auxPro = professorRepository.getById(id);
      auxPro.setName(aux.getName());
      auxPro.setLastName(aux.getLastName());
      return postProfessor(auxPro);
   }

   //=================Deletes=================//

   @Transactional
   public void deleteProfessor(@NotNull String id) {
      professorRepository.deleteById(id);
   }
}
