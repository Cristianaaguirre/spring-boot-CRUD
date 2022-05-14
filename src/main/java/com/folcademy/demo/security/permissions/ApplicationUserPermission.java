package com.folcademy.demo.security.permissions;

public enum ApplicationUserPermission {
   STUDENT_READ("student:read"),
   STUDENT_WRITE("student:write"),
   PROFESSOR_READ("professor:read"),
   PROFESSOR_WRITE("professor:write"),
   SUBJECT_READ("subject:read"),
   SUBJECT_WRITE("subject:write");

   private final String permission;

   ApplicationUserPermission(String permission) {
      this.permission = permission;
   }

   public String getPermission() {
      return permission;
   }
}
