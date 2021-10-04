package com.example.class6assignment.models;

import java.util.Date;

public class Assignment {
  private String name;
  private Date date;
  private double grade;

  public Assignment() {

  }

  public Assignment(String name, Date date, double grade) {
    this.name = name;
    this.date = date;
    this.grade = grade;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getGrade() {
    return grade;
  }

  public void setGrade(double grade) {
    this.grade = grade;
  }

}
