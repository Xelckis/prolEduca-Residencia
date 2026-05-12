package com.example.prol_educa.models;

import java.util.List;

public class JwtResponse {
  private String token;
  private List<String> roles;
  private int userId;

  public JwtResponse(String accessToken, List<String> roles, int userId) {
    this.token = "Bearer " + accessToken;
    this.roles = roles;
    this.userId = userId;
  }

  public JwtResponse(String accessToken) {
    this.token = "Bearer " + accessToken;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public List<String> getRoles() {
    return roles;
  }

  public int getUserId() {
    return userId;
  }

  public void serUserId(int userId) {
    this.userId = userId;
  }

}
