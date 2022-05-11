package com.folcademy.demo.exceptions;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(ResourceNotFoundException.class)
   protected ResponseEntity<Object> handleNotFound(@NotNull ResourceNotFoundException ex, WebRequest request) {
      ExceptionReturn aux = new ExceptionReturn(LocalDateTime.now(), ex.getMessage());
      return handleExceptionInternal(ex, aux, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
   }

   @ExceptionHandler(MyException.class)
   protected ResponseEntity<Object> handleErrorException(@NotNull MyException ex, WebRequest request) {
      ExceptionReturn aux = new ExceptionReturn(LocalDateTime.now(), ex.getMessage());
      return handleExceptionInternal(ex, aux, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
   }
}
