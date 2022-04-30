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
   @Getter
   private String id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String address;

   @Column(nullable = false)
   @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
   private List<Student> studentList;

   public School() {
   }

   public School(String id, String name, String address) {
      this.id = id;
      this.name = name;
      this.address = address;
   }
}
