package com.folcademy.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class School {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   @Column(nullable = false)
   private String name;
   @Column(nullable = false)
   private String address;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Student> studentList;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Professor> professorList;

   public School() {
   }

   public School(String name, String address) {
      this.name = name;
      this.address = address;
   }
}
