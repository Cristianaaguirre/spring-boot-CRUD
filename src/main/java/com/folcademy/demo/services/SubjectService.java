package com.folcademy.demo.services;

import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.entities.Professor;
import com.folcademy.demo.models.entities.Student;
import com.folcademy.demo.models.entities.Subject;
import com.folcademy.demo.models.repositories.SubjectRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

   @Autowired
   private SubjectRepository subjectRepository;
   @Autowired
   private ProfessorService professorService;
   @Autowired
   private StudentService studentService;

   //=================Gets=================//
   public Subject getSubject(Long id) {
      return subjectRepository
         .findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("DON'T FOUND"));
   }

   public List<Subject> getAllSubject() {
      return subjectRepository.findAll();
   }

   public List<Student> getStudentSubject(Long id) {
      return studentService
         .getStudents()
         .stream()
         .filter(student -> student.getSubject().contains(getSubject(id)))
         .collect(Collectors.toList());
   }

   //=================Post=================//
   @Transactional
   public Subject postSubject(@NotNull Subject aux) {
      return subjectRepository.save(aux);
   }
   //=================Put y Patch=================//
   @Transactional
   public Subject putSubject(@NotNull Subject subject, Long id) {
      Subject aux = getSubject(id);
      aux.setId(subject.getId());
      aux.setName(subject.getName());
      return subjectRepository.save(aux);
   }
   @Transactional
   public Subject setProfessor(Long idSb, String idPr){
      Subject subject = getSubject(idSb);
      Professor professor = professorService.getProfessor(idPr);
      if(professor.getSubject() == null) {
         professor.setSubject(subject);
         subject.setProfessor(professor);
      } else {
         professor.setSubject(null);
         subject.setProfessor(null);
      }
      professorService.postProfessor(professor);
      subjectRepository.save(subject);
      return subject;
   }
   @Transactional
   public Subject addStudent(Long idSb, String idSt) throws MyException {
      Student student = studentService.getStudent(idSt);
      Subject subject = getSubject(idSb);
      if(subject.getStudent().contains(student)) throw new MyException("the student is already in the subject");
      else {
         student.getSubject().add(subject);
         subject.getStudent().add(student);
         studentService.postStudent(student);
         subjectRepository.save(subject);
         return subject;
      }
   }
   @Transactional
   public void removeStudent(Long idSb, String idSt) {
      Student student = studentService.getStudent(idSt);
      Subject subject = getSubject(idSb);
      if(!subject.getStudent().contains(student)) throw new ResourceNotFoundException(idSt, "student");
      else subject.getStudent().remove(student);
   }
   //=================Delete=================//
   @Transactional
   public void deleteSubject(Long id) {
      subjectRepository.deleteById(id);
   }
}
