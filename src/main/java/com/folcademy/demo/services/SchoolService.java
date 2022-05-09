package com.folcademy.demo.services;

import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.models.Professor;
import com.folcademy.demo.models.School;
import com.folcademy.demo.models.Student;
import com.folcademy.demo.repositories.ProfessorRepository;
import com.folcademy.demo.repositories.SchoolRepository;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.repositories.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SchoolService {

   @Autowired
   private SchoolRepository schoolRepository;
   @Autowired
   private StudentRepository studentRepository;
   @Autowired
   private ProfessorRepository professorRepository;

   //=================Gets=================//

   @Transactional
   public @NotNull School getSchool(String idSchool){
      return schoolRepository
         .findById(idSchool)
         .orElseThrow(() -> new ResourceNotFoundException("School", idSchool));
   }

   @Transactional
   public List<School> getAllSubject() {
      return schoolRepository.findAll();
   }

   //=================Post=================//

   @Transactional
   public School postSchool(@RequestBody @NotNull School auxSchool) {
      return schoolRepository.save(auxSchool);
   }


   //=================Put y Patch=================//
   @Transactional
   public School modifySchool(@NotNull School auxSch, String id) {
      School school = getSchool(id);
      school.setName(auxSch.getName());
      school.setAddress(auxSch.getAddress());
      return postSchool(school);
   }

   @Transactional
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

   @Transactional
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

   @Transactional
   public void deleteSchool(String idSchool) {
      schoolRepository.deleteById(idSchool);
   }

   @Transactional
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

   @Transactional
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
}
