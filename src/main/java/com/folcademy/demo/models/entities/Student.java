package com.folcademy.demo.models.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Entity
@Builder
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private School school;
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Subject> subject;
}
