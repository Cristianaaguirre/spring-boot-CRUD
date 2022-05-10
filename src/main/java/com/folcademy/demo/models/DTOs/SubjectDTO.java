package com.folcademy.demo.models.DTOs;

import lombok.*;

@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SubjectDTO {
   private Long id;
   private String name;
}
