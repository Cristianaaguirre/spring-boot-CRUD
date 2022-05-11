package com.folcademy.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor @Getter
public class ExceptionReturn {
   private LocalDateTime localTime;
   private String message;
}
