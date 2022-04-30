package com.folcademy.demo.services;

import com.folcademy.demo.models.Student;
import com.folcademy.demo.models.Subject;
import com.folcademy.demo.repositories.StudentRepository;
import com.folcademy.demo.repositories.SubjectRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

   @Autowired
   private SubjectRepository subjectRepository;
   @Autowired
   private StudentService studentService;

   //=================Gets===================//

   @Transactional
   public @NotNull Subject getSubject(String idSubject) {
      Optional<Subject> auxOptional = subjectRepository.findById(idSubject);
      if (auxOptional.isEmpty()) throw new RuntimeException("OBJECT DON'T FOUND");
      return auxOptional.get();
   }

   @Transactional
   public List<Subject> getAllSubject() {
      return subjectRepository.findAll();
   }

   //=============Post====================//

   @Transactional
   public Subject postSubject(@RequestBody Subject auxSubject) {
      if (auxSubject == null) throw new RuntimeException("NULL OBJECT");
      else return subjectRepository.save(auxSubject);
   }


   //================Put y Patch========================//
   @Transactional
   public Subject putSubject(Subject auxSubject, String id) {
      Subject subject = getSubject(id);
      subject.setName(auxSubject.getName());
      subject.setAddress(auxSubject.getAddress());
      return postSubject(subject);
   }

   @Transactional
   public Subject patchStudent(@NotNull String idStudent, @NotNull String idSubject) throws Exception {
      Subject auxSb = getSubject(idSubject);
      Student auxSt = studentService.getStudent(idStudent);
      if (auxSt.getSubject() == null) {
         auxSb.getStudentList().add(auxSt);
         auxSt.setSubject(auxSb);
         studentService.postStudent(auxSt);
         postSubject(auxSb);
         return auxSb;
      } else throw new Exception("EL OBJETO YA POSEE UN COLEGIO");
   }

   //=================Delete=====================//

   @Transactional
   public void deleteSubject(String idSubject) {
      subjectRepository.deleteById(idSubject);
   }
}
