package com.folcademy.demo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
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
   @Column(name = "school_professor_list")
   private List<Professor> professorList;
}
