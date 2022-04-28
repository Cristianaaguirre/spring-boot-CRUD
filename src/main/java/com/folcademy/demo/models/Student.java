package com.folcademy.demo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Student {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   @Column
   private String name;
   @Column
   private String lastName;
   @Column
   private String email;
   @ManyToOne
   @JoinColumn(name = "subject_id")
   private Subject subject;

   public Student() {
   }

   public Student(String id, String name, String lastName, String email) {
      this.id = id;
      this.name = name;
      this.lastName = lastName;
      this.email = email;
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

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
