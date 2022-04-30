package com.folcademy.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Student {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String lastName;

   @Column(nullable = false)
   private String email;

   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private School school;

   public Student() {
   }

   public Student(String id, String name, String lastName, String email) {
      this.id = id;
      this.name = name;
      this.lastName = lastName;
      this.email = email;
   }
}
