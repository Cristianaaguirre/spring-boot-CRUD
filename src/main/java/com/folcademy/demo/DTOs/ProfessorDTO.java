package com.folcademy.demo.DTOs;

import lombok.*;

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProfessorDTO {
   private String id;
   private String name;
   private String lastName;
}
