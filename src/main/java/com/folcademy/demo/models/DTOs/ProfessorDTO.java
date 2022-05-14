package com.folcademy.demo.models.DTOs;

import com.folcademy.demo.models.entities.Subject;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProfessorDTO{
   private Long id;
   private String name;
   private String lastName;
   private List<Subject> subject;
}
