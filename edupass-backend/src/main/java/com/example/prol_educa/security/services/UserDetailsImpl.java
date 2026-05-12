package com.example.prol_educa.security.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.prol_educa.entities.Administrators;
import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.entities.Customers;

public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public UserDetailsImpl(Integer id, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super();
    this.id = id;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(Customers customers) {
    List<GrantedAuthority> authorities = customers.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getType().name())).collect(Collectors.toList());

    User user = new User(customers.getEmail(), customers.getPassword(), authorities);

    return new UserDetailsImpl(customers.getId(), user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public String getUsername() {
    return email;
  }

  public static UserDetailsImpl build(Administrators admin) {
    List<GrantedAuthority> authorities = admin.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getType().name())).collect(Collectors.toList());

    User user = new User(admin.getEmail(), admin.getPassword(), authorities);

    return new UserDetailsImpl(admin.getId(), user.getUsername(), user.getPassword(), authorities);
  }

  public static UserDetailsImpl build(Companies companies) {
    List<GrantedAuthority> authorities = companies.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getType().name())).collect(Collectors.toList());

    User user = new User(companies.getEmail(), companies.getPassword(), authorities);

    return new UserDetailsImpl(companies.getId(), user.getUsername(), user.getPassword(), authorities);
  }

}
