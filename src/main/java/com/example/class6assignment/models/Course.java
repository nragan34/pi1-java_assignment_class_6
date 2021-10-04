package com.example.class6assignment.models;

import java.util.List;

public class Course {
  private String name;
  private double averageGrade;
  private List<Assignment> assignments;

  public Course() {

  }

  public Course(String name, double averageGrade,
      List<Assignment> assignments) {
    this.name = name;
    this.averageGrade = averageGrade;
    this.assignments = assignments;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getAverageGrade() {
    return averageGrade;
  }

  public void setAverageGrade(double averageGrade) {
    this.averageGrade = averageGrade;
  }

  public List<Assignment> getAssignments() {
    return assignments;
  }

  public void setAssignments(List<Assignment> assignments) {
    this.assignments = assignments;
  }
}
