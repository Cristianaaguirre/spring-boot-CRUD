package com.folcademy.demo.services;

import com.folcademy.demo.exceptions.MyException;
import com.folcademy.demo.exceptions.ResourceNotFoundException;
import com.folcademy.demo.models.entities.Professor;
import com.folcademy.demo.models.entities.Student;
import com.folcademy.demo.models.entities.Subject;
import com.folcademy.demo.models.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor @Transactional
public class SubjectService {

   private SubjectRepository subjectRepository;
   private ProfessorService professorService;
   private StudentService studentService;

   //=================Gets=================//
   public Subject getSubject(Long id) {
      return subjectRepository
         .findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("DON'T FOUND"));
   }

   public List<Subject> getAllSubject() {
      List<Subject> aux = subjectRepository.findAll();
      if (aux.isEmpty()) throw new MyException("EMPTY LIST");
      else return aux;
   }

   public List<Student> getStudentSubject(Long id) {
      return studentService
         .getStudents()
         .stream()
         .filter(student -> student.getSubject().contains(getSubject(id)))
         .collect(Collectors.toList());
   }

   //=================Post=================//

   public Subject postSubject(@NotNull Subject aux) {
      return subjectRepository.save(aux);
   }

   //=================Put y Patch=================//

   public Subject putSubject(@NotNull Subject subject, Long id) {
      Subject aux = getSubject(id);
      aux.setId(subject.getId());
      aux.setName(subject.getName());
      return subjectRepository.save(aux);
   }

   public void addProfessor(Long idSb, String idPr){
      Subject subject = getSubject(idSb);
      Professor professor = professorService.getProfessor(idPr);
      if(professor.getSubject() != null) throw new MyException("the professor has a subject");
      else {
         subject.setProfessor(professor);
         professor.setSubject(subject);
         subjectRepository.save(subject);
         professorService.postProfessor(professor);
      }
   }

   public void removeProfessor(Long idSb, String idPr){
      Subject subject = getSubject(idSb);
      Professor professor = professorService.getProfessor(idPr);
      if(professor.getSubject() == null) throw new MyException("the professor don't have a subject");
      else {
         subject.setProfessor(null);
         professor.setSubject(null);
         subjectRepository.save(subject);
         professorService.postProfessor(professor);
      }
   }

   public void addStudent(Long idSb, String idSt){
      Student student = studentService.getStudent(idSt);
      Subject subject = getSubject(idSb);
      if(subject.getStudent().contains(student)) throw new MyException("the student is already in the subject");
      else {
         student.getSubject().add(subject);
         subject.getStudent().add(student);
         studentService.postStudent(student);
         subjectRepository.save(subject);
      }
   }

   public void removeStudent(Long idSb, String idSt) {
      Student student = studentService.getStudent(idSt);
      Subject subject = getSubject(idSb);
      if(!subject.getStudent().contains(student)) throw new MyException("the student don't already in the subject");
      else {
         subject.getStudent().remove(student);
         student.getSubject().remove(subject);
         studentService.postStudent(student);
         subjectRepository.save(subject);
      }
   }
   //=================Delete=================//

   public void deleteSubject(Long id) {
      subjectRepository.deleteById(id);
   }
}
