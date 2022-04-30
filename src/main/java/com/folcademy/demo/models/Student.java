package com.folcademy.demo.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Student {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   @Getter private String id;

   @Column(nullable = false)
   @Getter @Setter private String name;

   @Column(nullable = false)
   @Getter @Setter private String lastName;

   @Column(nullable = false)
   @Getter @Setter private String email;

   @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REFRESH})
   @JoinColumn(name = "subject")
   @Getter @Setter private Subject subject;

   public Student() {
   }

   public Student(String id, String name, String lastName, String email) {
      this.id = id;
      this.name = name;
      this.lastName = lastName;
      this.email = email;
   }
}
