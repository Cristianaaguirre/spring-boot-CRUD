package com.folcademy.demo.security.permissions;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.folcademy.demo.security.permissions.ApplicationUserPermission.*;

public enum ApplicationUserRol {
   STUDENT(Sets.newHashSet(STUDENT_READ, SUBJECT_READ, STUDENT_WRITE)),
   PROFESSOR(Sets.newHashSet(STUDENT_READ, SUBJECT_READ, PROFESSOR_WRITE)),
   ADMIN(Sets.newHashSet(SUBJECT_READ, STUDENT_WRITE, SUBJECT_READ, SUBJECT_WRITE, PROFESSOR_READ, PROFESSOR_WRITE));

   private final Set<ApplicationUserPermission> permissions;

   ApplicationUserRol(Set<ApplicationUserPermission> permissions) {
      this.permissions = permissions;
   }

   public Set<ApplicationUserPermission> getPermissions() {
      return permissions;
   }

   public Set<SimpleGrantedAuthority> getAuthorities () {
      Set<SimpleGrantedAuthority> authorities =
         getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
      authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
      return authorities;
   }
}
