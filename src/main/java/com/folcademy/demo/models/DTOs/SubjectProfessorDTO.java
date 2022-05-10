package com.folcademy.demo.models.DTOs;

import lombok.*;

@Data
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SubjectProfessorDTO {
   private Long id;
   private String name;
   private String professorName;
}
