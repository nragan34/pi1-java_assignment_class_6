package com.example.class6assignment.usecases;

import com.example.class6assignment.models.Assignment;
import com.example.class6assignment.models.Course;
import com.example.class6assignment.models.Student;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.example.class6assignment.ThymeleafConfig.CSV_TEMPLATE_ENGINE;


@Component
public class StudentsCsvUseCase {


  private final TemplateEngine templateEngine;

  @Autowired
  public StudentsCsvUseCase(@Qualifier(CSV_TEMPLATE_ENGINE) TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;

  }


  public List<Student> getStudentData() {

    List<Student> students = new ArrayList<>();
    List<Course> courses = new ArrayList<>();
    List<Assignment> assignments = new ArrayList<>();

    // hashmap to contain all data
    HashMap<List<Student>, HashMap<List<Course>, List<Assignment>>> allObjects = new HashMap<>();

    ////////////////////////////////////////
    //// Student 1 Assignments
    //////////////////////////////////////

    Assignment kentSmothermanCalculusAssignment1 = new Assignment("Area under the curve",
        new Date(121, 9, 2), 4);

    Assignment kentSmothermanCalculusAssignment2 = new Assignment("Acceleration",
        new Date(121, 9, 4), 3.9);

    Assignment kentSmothermanCalculusAssignment3 = new Assignment("Dual-axis Equations",
        new Date(121, 9, 6), 3.6);

    Assignment kentSmothermanAvancedJavaAssignment1 = new Assignment("Algorithms",
        new Date(121, 9, 3), 3.2);

    Assignment kentSmothermanAvancedJavaAssignment2 = new Assignment("Algorithms",
        new Date(121, 9, 5), 3.7);

    assignments.add(kentSmothermanCalculusAssignment1);
    assignments.add(kentSmothermanCalculusAssignment2);
    assignments.add(kentSmothermanCalculusAssignment3);
    assignments.add(kentSmothermanAvancedJavaAssignment1);
    assignments.add(kentSmothermanAvancedJavaAssignment2);


    // Calculus Course
    Course kentSmothermanCalculus = new Course("Advanced Java", 0,
        List.of(kentSmothermanCalculusAssignment1, kentSmothermanCalculusAssignment2,
            kentSmothermanCalculusAssignment3));

    // Advanced Java Course
    Course kentSmothermanAdvancedJava = new Course("Advanced Java", 0,
        List.of(kentSmothermanAvancedJavaAssignment1, kentSmothermanAvancedJavaAssignment2));

    // Add Courses to List
    courses.add(kentSmothermanCalculus);
    courses.add(kentSmothermanAdvancedJava);

    // Add Courses to Student 1
    Student kentSmotherman = new Student("Kent", "Smotherman",
        List.of(kentSmothermanCalculus, kentSmothermanAdvancedJava));


    ////////////////////////////////////////
    //// Student 2 Assignments
    //////////////////////////////////////

    Assignment norvilleRogersCalculusAssignment1 = new Assignment("Area under the curve",
        new Date(121, 9, 2), 2.8);

    Assignment norvilleRogersCalculusAssignment2 = new Assignment("Acceleration",
        new Date(121, 9, 4), 3.6);

    Assignment norvilleRogersCalculusAssignment3 = new Assignment("Dual-axis Equations",
        new Date(121, 9, 6), 3.2);

    Assignment norvilleRogersJava101Assignment1 = new Assignment("Syntax", new Date(121, 9, 3),
        3.5);

    Assignment norvilleRogersJava101Assignment2 = new Assignment("Control Structures",
        new Date(121, 9, 5), 3.1);

    assignments.add(norvilleRogersCalculusAssignment1);
    assignments.add(norvilleRogersCalculusAssignment2);
    assignments.add(norvilleRogersCalculusAssignment3);
    assignments.add(norvilleRogersJava101Assignment1);
    assignments.add(norvilleRogersJava101Assignment1);

    // Calculus Course
    Course norvilleRogersCalculusCourse = new Course("Calculus", 0,
        List.of(norvilleRogersCalculusAssignment1,
            norvilleRogersCalculusAssignment2, norvilleRogersCalculusAssignment3));

    // Java101 Course
    Course norvilleRogersJava101Course = new Course("Java101", 0,
        List.of(norvilleRogersJava101Assignment1, norvilleRogersJava101Assignment2));

    // Add courses to list
    courses.add(norvilleRogersCalculusCourse);
    courses.add(norvilleRogersJava101Course);

    // Add Courses to Student 2
    Student norvilleRogers = new Student("Norville", "Rogers",
        List.of(norvilleRogersCalculusCourse, norvilleRogersJava101Course));

    ////////////////////////////////////////
    //// Add Students to students list
    //////////////////////////////////////

    students.add(kentSmotherman);
    students.add(norvilleRogers);

    return students;
  }


  // grades are 1-4 average the grades between 1-4. 4 being the best grade.
  public List<Student> setGradeAverage() {


    // store total point value and the total points the student received
    List<Student> students = getStudentData();

    // Student / Course
    HashMap<Student, List<Course>> studentCourseMap = new HashMap<>();
    List<Course> courses = new ArrayList<>();

    // Course / Assignment
    HashMap<Course,List<Assignment>> courseAssignmentMap = new HashMap<>();
    List<Assignment> assignments = new ArrayList<>();


    // for each assignment add 4 to the total
    double totalPointValue = 0;
    double totalOfStudentGrade = 0;

    // Create maps of things
    for (Student student : students) {
        student.getCourses().stream().forEach(e -> {
          studentCourseMap.put(student, List.of(e));
          courses.add(e);
          //
          e.getAssignments().stream().forEach(a -> {
            courseAssignmentMap.put(e, List.of(a));
            assignments.add(a);
          });
        });
    }

    // Print stuff
    System.out.println(studentCourseMap);
    System.out.println("------------------");
    System.out.println(courseAssignmentMap);

    // now we can use the objects in the maps to figure out what the average is


    // average
    double averageGrade = (100 * (totalOfStudentGrade / totalPointValue));

//          // total grade for student
//          totalOfStudentGrade = assignment.getGrade();
//          System.out.println("Total of student grade..... " + totalOfStudentGrade);
//
//          // average
//          double averageGrade = 100 * (totalOfStudentGrade / totalPointValue);
//          course.setAverageGrade(averageGrade);

    return students;
  }


  // student last name, student first name, course name, overall course average grade,
// course average grade as of this assignment (in date order), assignment name,
// assignment date, assignment grade
  public String getStudentsAsCsv() {
    setGradeAverage();
    // create student/courses/assignments
    List<Student> students = getStudentData();

    StringWriter writer = new StringWriter();
    Context context = new Context();
    context.setVariable("students", students);
    templateEngine.process("students", context, writer);
    return writer.toString();
  }

//  // Course name, student last name, student first name, average grade
//  public void getCoursesAsCsv() {
//    List<Student> students = getStudentData();
//
//    //List<Student> studentWithGradeAverage = new ArrayList<>();
//    HashMap<String, List<Student>> courseMapping = new HashMap<>();
//    // // course name, student last name, student first name, average grade
//
//    // loop through students
//    for (Student student : students) {
//      // loop through courses for each student
//      for (Course course : student.getCourses()) {
//        // if courseMapping does NOT contain a key which is a course name
//        if (!courseMapping.containsKey(course.getName())) {
//          // create a new course entry in courseMapping HashMap
//          courseMapping.put(course.getName(), new ArrayList<>());
//        }
//        // get key of courseMapping by name and add a value (student)
//        courseMapping.get(course.getName()).add(student);
//      }
//    }
//  }


}
