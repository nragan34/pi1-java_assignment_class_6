package com.example.class6assignment.models;

public class CourseCsv {
  private String courseName;
  private String lastName;
  private String firstName;
  private double averageGrade;


  public CourseCsv() {

  }

  public CourseCsv(String courseName, String lastName, String firstName, double averageGrade) {
    this.courseName = courseName;
    this.lastName = lastName;
    this.firstName = firstName;
    this.averageGrade = averageGrade;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public double getAverageGrade() {
    return averageGrade;
  }

  public void setAverageGrade(double averageGrade) {
    this.averageGrade = averageGrade;
  }
}
