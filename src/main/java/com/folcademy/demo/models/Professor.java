package com.folcademy.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Professor {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   @Column(nullable = false)
   private String name;
   @Column(nullable = false)
   private String lastName;

   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private School school;

   public Professor() {
   }

   public Professor(String id, String name, String lastName) {
      this.id = id;
      this.name = name;
      this.lastName = lastName;
   }
}
