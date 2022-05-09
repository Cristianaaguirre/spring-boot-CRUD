package com.folcademy.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Professor {
   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   @Column(nullable = false)
   private String name;
   @Column(nullable = false)
   private String lastName;
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private School school;
}
