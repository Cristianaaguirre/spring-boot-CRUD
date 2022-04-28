package com.folcademy.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Subject {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   @Column(nullable = false)
   private String name;
   @OneToMany
   private List<Student> studentList;

   public Subject() {
   }

   public Subject(String id, String name, List<Student> studentList) {
      this.id = id;
      this.name = name;
      this.studentList = studentList;
   }

   public List<Student> getStudentList() {
      return studentList;
   }

   public void setStudentList(List<Student> studentList) {
      this.studentList = studentList;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


}
