package com.folcademy.demo.services;

import com.folcademy.demo.models.Subject;
import com.folcademy.demo.repositories.SubjectRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

   private SubjectRepository subjectRepository;
   private StudentService studentService;

   @Transactional
   public @NotNull Subject getSubject(String idSubject) {
      Optional<Subject> auxOptional = subjectRepository.findById(idSubject);
      if(auxOptional.isEmpty()) throw new RuntimeException("OBJECT DON'T FOUND");
      return auxOptional.get();
   }

   @Transactional
   public void deleteSubject(String idSubject) {
      subjectRepository.deleteById(idSubject);
   }

   @Transactional
   public List<Subject> getAllSubject() {
      return subjectRepository.findAll();
   }

   @Transactional
   public void postSubject(Subject auxSubject) {
      if(auxSubject == null) throw new RuntimeException("NULL OBJECT");
      else subjectRepository.save(auxSubject);
   }
}
