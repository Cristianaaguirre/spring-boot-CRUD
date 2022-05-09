package com.folcademy.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ResourceNotFoundException extends RuntimeException{
   public ResourceNotFoundException(String message) {
      super(message);
   }
   public ResourceNotFoundException(String messageFormat, Object... args) {
      super(String.format(messageFormat, args));
   }
   public ResourceNotFoundException(String id, String entity) {
      super("No " + entity + " found with ID " + id);
   }
   public ResourceNotFoundException() {
   }
}
