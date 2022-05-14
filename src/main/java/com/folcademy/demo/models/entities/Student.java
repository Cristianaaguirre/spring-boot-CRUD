package com.folcademy.demo.models.entities;

import lombok.*;

import javax.persistence.Entity;
@Entity
@Builder
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Student extends User{
}
