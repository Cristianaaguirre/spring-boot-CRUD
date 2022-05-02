package com.folcademy.demo.services;

import com.folcademy.demo.models.Professor;
import com.folcademy.demo.models.Student;
import com.folcademy.demo.models.School;
import com.folcademy.demo.repositories.SchoolRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

   @Autowired
   private SchoolRepository schoolRepository;
   @Autowired
   private StudentService studentService;
   @Autowired
   private ProfessorService professorService;

   //=================Gets=================//

   @Transactional
   public @NotNull School getSchool(String idSchool) {
      Optional<School> auxOptional = schoolRepository.findById(idSchool);
      if (auxOptional.isEmpty()) throw new RuntimeException("DON'T FOUND");
      return auxOptional.get();
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
   public School addStudent(@NotNull String idSt, @NotNull String idSch) throws Exception {
      Student auxSt = studentService.getStudent(idSt);
      if (auxSt.getSchool() != null) throw new Exception("the student already has a school");
      else {
         School auxSch = getSchool(idSch);
         auxSch.getStudentList().add(auxSt);
         auxSt.setSchool(auxSch);
         studentService.postStudent(auxSt);
         return postSchool(auxSch);
      }
   }

   @Transactional
   public School addProfessor(@NotNull String idPr, @NotNull String idSch) throws Exception {
      Professor auxPro = professorService.getProfessor(idPr);
      if (auxPro.getSchool() != null) throw new Exception("the professor already has a school");
      else {
         School auxSch = getSchool(idSch);
         auxSch.getProfessorList().add(auxPro);
         auxPro.setSchool(auxSch);
         professorService.postProfessor(auxPro);
         return postSchool(auxSch);
      }
   }

   //=================Delete=================//

   @Transactional
   public void deleteSchool(String idSchool) {
      schoolRepository.deleteById(idSchool);
   }

   @Transactional
   public School deleteStudent(String idSch, String idSt) throws Exception {
      Student auxSt = studentService.getStudent(idSt);
      if (auxSt.getSchool() == null) throw new Exception("the student don't have a school");
      else {
         School auxSch = schoolRepository.getById(idSch);
         auxSch.getStudentList().remove(studentService.getStudent(idSt));
         auxSt.setSchool(null);
         studentService.postStudent(auxSt);
         return schoolRepository.save(auxSch);
      }
   }

   @Transactional
   public School deleteProfessor(String idpr, String idSch) throws Exception {
      Professor auxPr = professorService.getProfessor(idpr);
      if (auxPr.getSchool() == null) throw new Exception("the professor don't have a school");
      else {
         School auxSch = schoolRepository.getById(idSch);
         auxSch.getProfessorList().remove(auxPr);
         auxPr.setSchool(null);
         professorService.postProfessor(auxPr);
         return schoolRepository.save(auxSch);
      }
   }
}
