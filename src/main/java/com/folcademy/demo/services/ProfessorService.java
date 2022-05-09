package com.folcademy.demo.services;


import com.folcademy.demo.DTOs.ProfessorDTO;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.Professor;
import com.folcademy.demo.repositories.ProfessorRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProfessorService {

   @Autowired
   private ProfessorRepository professorRepository;

   //=================Gets=================//

   public @NotNull ProfessorDTO getProfessor(String id){
      Professor aux =
         professorRepository
         .findById(id)
         .orElseThrow(() -> new ResourceNotFoundException(id, "professor"));
      return toDTO(aux);
   }

   public List<ProfessorDTO> getAllProfessors(){
      return professorRepository
         .findAll()
         .stream()
         .map(this::toDTO)
         .collect(Collectors.toList());
   }

   //=================Post=================//
   @Transactional
   public ProfessorDTO postProfessor(@NotNull @RequestBody Professor auxPro) {
      return toDTO(professorRepository.save(auxPro));
   }

   //=================Puts y Patches=================//

   @Transactional
   public ProfessorDTO putProfessor(@NotNull Professor aux, String id) {
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
   private ProfessorDTO toDTO(@NotNull Professor aux) {
         return ProfessorDTO.builder()
            .id(aux.getId())
            .name(aux.getName())
            .lastName(aux.getLastName())
            .build();
   }
}
