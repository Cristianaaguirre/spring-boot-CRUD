package com.folcademy.demo.models.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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
   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Subject subject;
}
