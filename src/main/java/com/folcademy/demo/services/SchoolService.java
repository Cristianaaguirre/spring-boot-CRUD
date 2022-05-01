package com.folcademy.demo.services;

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

   //=================Gets=================//

   @Transactional
   public @NotNull School getSchool(String idSchool) {
      Optional<School> auxOptional = schoolRepository.findById(idSchool);
      if (auxOptional.isEmpty()) throw new RuntimeException("OBJECT DON'T FOUND");
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
   public School putSchool(@NotNull School auxSch, String id) {
      School school = getSchool(id);
      school.setName(auxSch.getName());
      school.setAddress(auxSch.getAddress());
      return postSchool(school);
   }

   @Transactional
   public School patchStudent(@NotNull String idSt, @NotNull String idSch) throws Exception {
      School auxSch = getSchool(idSch);
      Student auxSt = studentService.getStudent(idSt);
      if (auxSt.getSchool() != null) throw new Exception("the student already has a school");
      auxSch.getStudentList().add(auxSt);
      auxSt.setSchool(auxSch);
      studentService.postStudent(auxSt);
      postSchool(auxSch);
      return auxSch;
   }

   //=================Delete=================//

   @Transactional
   public void deleteSchool(String idSchool) {
      schoolRepository.deleteById(idSchool);
   }

   @Transactional
   public School deleteStudent(String idSch, String idSt) throws Exception {
      School auxSch = schoolRepository.getById(idSch);
      Student auxSt = studentService.getStudent(idSt);
      auxSt.setSchool(null);
      studentService.postStudent(auxSt);
      auxSch.getStudentList().remove(studentService.getStudent(idSt));
      return schoolRepository.save(auxSch);
   }
}
