package com.folcademy.demo.models;

import lombok.Getter;
import lombok.Setter;
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
   @Getter @Setter private String name;
   @Column(nullable = false)
   @Getter @Setter private String address;
   @OneToMany
   private List<Student> studentList;

   public Subject() {
   }
   public Subject(String id, String name, String address) {
      this.id = id;
      this.name = name;
      this.address = address;
   }
}
