package com.example.class6assignment.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private List<Course> courses;

    public Student(String firstName, String lastName,
        List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    //    private String lastName;
//    private String firstName;
//    private Date birthday;
//    private Date enrollmentDate;
//    private BigDecimal grade;
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }
//
//    public Date getEnrollmentDate() {
//        return enrollmentDate;
//    }
//
//    public void setEnrollmentDate(Date enrollmentDate) {
//        this.enrollmentDate = enrollmentDate;
//    }
//
//    public BigDecimal getGrade() {
//        return grade;
//    }
//
//    public void setGrade(BigDecimal grade) {
//        this.grade = grade;
//    }
}
