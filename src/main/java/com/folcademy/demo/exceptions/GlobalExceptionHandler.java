package com.folcademy.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(ResourceNotFoundException.class)
   protected ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex, WebRequest request, HttpServletRequest uri) {
      ExceptionReturn aux = new ExceptionReturn(LocalDateTime.now(), ex.getMessage(), uri.getRequestURI());
      return handleExceptionInternal(ex, aux, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
   }

   @ExceptionHandler(MyException.class)
   protected ResponseEntity<Object> handleErrorException(MyException ex, WebRequest request, HttpServletRequest uri) {
      ExceptionReturn aux = new ExceptionReturn(LocalDateTime.now(), ex.getMessage(), uri.getRequestURI());
      return handleExceptionInternal(ex, aux, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
   }
}
