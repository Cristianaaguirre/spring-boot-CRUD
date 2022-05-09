package com.folcademy.demo.DTOs;

import lombok.*;
@Data
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class StudentDTO {
   private String id;
   private String name;
   private String lastName;
   private String email;
}