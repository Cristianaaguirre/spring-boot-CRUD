package com.folcademy.demo.services;


import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.DTOs.ProfessorDTO;
import com.folcademy.demo.models.entities.Professor;
import com.folcademy.demo.models.repositories.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class ProfessorService {

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

   //=================DTOs=================//
   public ProfessorDTO toDTO(@NotNull Professor aux) {
      return
         ProfessorDTO.builder()
            .id(aux.getId())
            .name(aux.getName())
            .lastName(aux.getLastName())
            .subject(aux.getSubject())
            .build();
   }
   public List<ProfessorDTO> listToDTO(@NotNull List<Professor> list){
      return list
         .stream()
         .map(this::toDTO)
         .collect(Collectors.toList());
   }

}
