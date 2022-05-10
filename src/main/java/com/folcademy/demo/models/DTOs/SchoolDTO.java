package com.folcademy.demo.DTOs;

import lombok.*;

@Data
@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SchoolDTO {
   private String id;
   private String name;
   private String address;
}
