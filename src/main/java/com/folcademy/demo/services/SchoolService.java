package com.folcademy.demo.services;

import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.DTOs.SchoolDTO;
import com.folcademy.demo.models.entities.Professor;
import com.folcademy.demo.models.entities.School;
import com.folcademy.demo.models.entities.Student;
import com.folcademy.demo.models.repositories.ProfessorRepository;
import com.folcademy.demo.models.repositories.SchoolRepository;
import com.folcademy.demo.models.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor @Transactional
public class SchoolService {

   private SchoolRepository schoolRepository;
   private StudentRepository studentRepository;
   private ProfessorRepository professorRepository;

   //=================Gets=================//

   public @NotNull School getSchool(String idSchool){
      return schoolRepository
         .findById(idSchool)
         .orElseThrow(() -> new ResourceNotFoundException("School", idSchool));
   }
   public List<School> getAllSchool() {
      return schoolRepository.findAll();
   }

   //=================Post=================//

   public School postSchool(@RequestBody @NotNull School auxSchool) {
      return schoolRepository.save(auxSchool);
   }

   //=================Put y Patch=================//

   public School modifySchool(@NotNull School auxSch, String id) {
      School school = getSchool(id);
      school.setName(auxSch.getName());
      school.setAddress(auxSch.getAddress());
      return postSchool(school);
   }

   public School addStudent(@NotNull String idSt, @NotNull String idSch) throws MyException {
      Student auxSt = studentRepository.getById(idSt);
      if (auxSt.getSchool() != null) throw new MyException("the student already has a school");
      else {
         School auxSch = getSchool(idSch);
         auxSch.getStudentList().add(auxSt);
         auxSt.setSchool(auxSch);
         studentRepository.save(auxSt);
         return postSchool(auxSch);
      }
   }

   public School addProfessor(@NotNull String idPr, @NotNull String idSch) throws MyException {
      Professor auxPro = professorRepository.getById(idPr);
      if (auxPro.getSchool() != null) throw new MyException("the professor already has a school");
      else {
         School auxSch = getSchool(idSch);
         auxSch.getProfessorList().add(auxPro);
         auxPro.setSchool(auxSch);
         professorRepository.save(auxPro);
         return postSchool(auxSch);
      }
   }
   //=================Delete=================//

   public void deleteSchool(String idSchool) {
      schoolRepository.deleteById(idSchool);
   }


   public void deleteStudent(String idSch, String idSt) throws MyException {
      Student auxSt = studentRepository.getById(idSt);
      if (auxSt.getSchool() == null) throw new MyException("the student don't have a school");
      else {
         School auxSch = schoolRepository.getById(idSch);
         auxSch.getStudentList().remove(studentRepository.getById(idSt));
         auxSt.setSchool(null);
         studentRepository.save(auxSt);
      }
   }

   public void deleteProfessor(String idpr, String idSch) throws MyException {
      Professor auxPr = professorRepository.getById(idpr);
      if (auxPr.getSchool() == null) throw new MyException("the professor don't have a school");
      else {
         School auxSch = schoolRepository.getById(idSch);
         auxSch.getProfessorList().remove(auxPr);
         auxPr.setSchool(null);
         professorRepository.save(auxPr);
      }
   }
   //=================DTOs method=================//
   public SchoolDTO toDTO(@NotNull School aux) {
      return SchoolDTO.builder()
         .id(aux.getId())
         .name(aux.getName())
         .address(aux.getAddress())
         .build();
   }
   public List<SchoolDTO> listToDTO(@NotNull List<School> list) {
      return list
         .stream()
         .map(this::toDTO)
         .collect(Collectors.toList());
   }
}
