package com.example.prol_educa.models;

import java.sql.Date;

public class RegistrationsDto {

  private Integer scholarshipHolderId;
  private Integer courseId;
  private Date registrationDate;
  private Integer statusId;

  public Integer getScholarshipHolderId() {
    return scholarshipHolderId;
  }

  public void setScholarshipHolderId(Integer scholarshipHolderId) {
    this.scholarshipHolderId = scholarshipHolderId;
  }

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public Integer getStatusId() {
    return statusId;
  }

  public void setStatusId(Integer statusId) {
    this.statusId = statusId;
  }

  public RegistrationsDto(Integer scholarshipHolderId, Integer courseId, Date registrationDate, Integer statusId) {
    this.scholarshipHolderId = scholarshipHolderId;
    this.courseId = courseId;
    this.registrationDate = registrationDate;
    this.statusId = statusId;
  }

  public RegistrationsDto() {
  }

}
