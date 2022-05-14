package com.folcademy.demo.models.entities;

import com.folcademy.demo.security.permissions.ApplicationUserRol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
   private String lastName;
   private String username;
   private String email;
   private String password;
   private ApplicationUserRol rol;
   private Boolean locked;
   private Boolean enable;
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private School school;
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private List<Subject> subject;
   
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return rol.getAuthorities();
   }

   @Override
   public String getPassword() {return password;}

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return locked;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return enable;
   }
}
