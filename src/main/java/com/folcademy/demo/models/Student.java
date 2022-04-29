package com.folcademy.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
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
}
