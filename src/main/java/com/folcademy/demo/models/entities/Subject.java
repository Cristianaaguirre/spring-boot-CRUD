package com.folcademy.demo.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Professor professor;
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Student> student;
}
